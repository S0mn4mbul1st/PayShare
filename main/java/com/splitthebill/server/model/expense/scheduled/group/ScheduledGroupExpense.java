package com.splitthebill.server.model.expense.scheduled.group;

import com.splitthebill.server.dto.expense.scheduled.group.ScheduledExpenseParticipantReadDto;
import com.splitthebill.server.model.Currency;
import com.splitthebill.server.model.Group;
import com.splitthebill.server.model.expense.Expense;
import com.splitthebill.server.model.expense.scheduled.Schedule;
import com.splitthebill.server.model.expense.scheduled.group.ScheduledPersonGroupExpense;
import com.splitthebill.server.model.user.Person;
import com.splitthebill.server.model.user.PersonGroup;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ScheduledGroupExpense extends Expense {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    
    private BigDecimal amount;
    
    @ManyToOne
    private Group group;

    private Currency currency;
    
    @ManyToOne
    private PersonGroup creditor;

    @ManyToOne(cascade = CascadeType.ALL)
    private Schedule schedule;

    @OneToMany(mappedBy = "scheduledGroupExpense", cascade = CascadeType.ALL)
    List<ScheduledPersonGroupExpense> scheduledPersonGroupExpenses;

	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public Group getGroup() {
		// TODO Auto-generated method stub
		return this.group;
	}

	public List<ScheduledPersonGroupExpense> getScheduledPersonGroupExpenses() {
		// TODO Auto-generated method stub
		return this.scheduledPersonGroupExpenses;
	}

	public Schedule getSchedule() {
		// TODO Auto-generated method stub
		return this.schedule;
	}

	public void setCurrency(Currency currency) {
		// TODO Auto-generated method stub
		this.currency = currency;
	}

	public void setGroup(Group group2) {
		// TODO Auto-generated method stub
		this.group = group2;
	}

	public void setCreditor(PersonGroup creditor2) {
		// TODO Auto-generated method stub
		this.creditor = creditor;
	}

	public void setAmount(BigDecimal amount) {
		// TODO Auto-generated method stub
		this.amount = amount;
	}

	public void setTitle(String title) {
		// TODO Auto-generated method stub
		this.title = title;
	}

	public void setSchedule(Schedule schedule2) {
		// TODO Auto-generated method stub
		this.schedule = schedule2;
	}

	public void setScheduledPersonGroupExpenses(LinkedList<ScheduledPersonGroupExpense> debtors) {
		// TODO Auto-generated method stub
		this.scheduledPersonGroupExpenses = debtors;
	}

	public PersonGroup getCreditor() {
		// TODO Auto-generated method stub
		return this.creditor;
	}

	public @NotBlank String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	public @DecimalMin("0.01") BigDecimal getAmount() {
		// TODO Auto-generated method stub
		return this.amount;
	}

	public Object getCurrency() {
		// TODO Auto-generated method stub
		return this.currency;
	}

}