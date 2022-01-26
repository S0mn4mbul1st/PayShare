package com.payshare.server.service;

import com.payshare.server.dto.expense.OwnExpenseCreateDto;
import com.payshare.server.model.Currency;
import com.payshare.server.model.expense.OwnExpense;
import com.payshare.server.model.user.Person;
import com.payshare.server.repository.CurrencyRepository;
import com.payshare.server.repository.OwnExpenseRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class OwnExpenseService {

    @NonNull
    private OwnExpenseRepository ownExpenseRepository;

    @NonNull
    private CurrencyRepository currencyRepository;

    public OwnExpense addExpense(OwnExpenseCreateDto expenseDto, Person person) {
        OwnExpense ownExpense = new OwnExpense();
        Currency currency = currencyRepository.findCurrencyByAbbreviation(
                expenseDto.getCurrencyAbbreviation())
                .orElseThrow(() -> new EntityNotFoundException("Currency not found"));
        ownExpense.setCurrency(currency);
        ownExpense.setOwner(person);
        ownExpense.setAmount(expenseDto.getAmount());
        ownExpense.setTitle(expenseDto.getTitle());
        ownExpense.setReceiptPhoto(expenseDto.getReceiptPhoto());
        person.addOwnExpense(ownExpense);
        return ownExpenseRepository.save(ownExpense);
    }

}
