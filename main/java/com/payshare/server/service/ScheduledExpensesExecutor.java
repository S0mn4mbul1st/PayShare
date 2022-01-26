package com.payshare.server.service;

import com.payshare.server.dto.expense.OwnExpenseCreateDto;
import com.payshare.server.dto.group.ExpenseParticipantCreateDto;
import com.payshare.server.dto.group.GroupExpenseCreateDto;
import com.payshare.server.model.expense.scheduled.group.ScheduledGroupExpense;
import com.payshare.server.model.expense.scheduled.own.ScheduledOwnExpense;
import com.payshare.server.repository.ScheduleRepository;
import com.payshare.server.repository.ScheduledGroupExpenseRepository;
import com.payshare.server.repository.ScheduledOwnExpenseRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduledExpensesExecutor {

    @NonNull
    private final GroupService groupService;

    @NonNull
    private final OwnExpenseService ownExpenseService;

    @NonNull
    private final ScheduledGroupExpenseRepository scheduledGroupExpenseRepository;

    @NonNull
    private final ScheduledOwnExpenseRepository scheduledOwnExpenseRepository;

    @NonNull
    private final ScheduleRepository scheduleRepository;

    @Transactional
    @Scheduled(cron = "${payshare.app.scheduledExpensesCron}")
    public void performScheduledExpenses() {
        LocalDate now = LocalDate.now();
        List<ScheduledGroupExpense> scheduledGroupExpenses = scheduledGroupExpenseRepository
                .findAllWithNextTriggerDateBefore(now);
        scheduledGroupExpenses.forEach(scheduledExpense -> {
            GroupExpenseCreateDto createDto = new GroupExpenseCreateDto();
            createDto.title = scheduledExpense.getTitle();
            createDto.creditorId = scheduledExpense.getCreditor().getId();
            createDto.amount = scheduledExpense.getAmount();
            createDto.currencyAbbreviation = scheduledExpense.getCurrency().getAbbreviation();
            createDto.debtors = new ArrayList<>();
            scheduledExpense.getScheduledPersonGroupExpenses().forEach(
                    personGroup -> {
                        ExpenseParticipantCreateDto expenseParticipantCreateDto = new ExpenseParticipantCreateDto();
                        expenseParticipantCreateDto.debtorId = personGroup.getDebtor().getId();
                        expenseParticipantCreateDto.weight = personGroup.getWeight();
                        createDto.debtors.add(expenseParticipantCreateDto);
                    });
            groupService.addExpense(scheduledExpense.getGroup().getId(), createDto,
                    scheduledExpense.getCreditor().getPerson());
            scheduledExpense.getSchedule().tickTrigger();
            scheduleRepository.save(scheduledExpense.getSchedule());
        });

        List<ScheduledOwnExpense> scheduledOwnExpenses = scheduledOwnExpenseRepository
                .findAllWithNextTriggerDateBefore(now);
        scheduledOwnExpenses.forEach(scheduledExpense -> {
            OwnExpenseCreateDto createDto = new OwnExpenseCreateDto();
            createDto.setTitle(scheduledExpense.getTitle());
            createDto.setAmount(scheduledExpense.getAmount());
            createDto.setCurrencyAbbreviation(scheduledExpense.getCurrency().getAbbreviation());
            ownExpenseService.addExpense(createDto, scheduledExpense.getPerson());
            scheduledExpense.getSchedule().tickTrigger();
            scheduleRepository.save(scheduledExpense.getSchedule());
        });
    }
}
