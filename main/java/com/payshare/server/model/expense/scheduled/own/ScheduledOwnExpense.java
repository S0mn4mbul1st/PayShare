package com.payshare.server.model.expense.scheduled.own;

import com.payshare.server.model.Currency;
import com.payshare.server.model.expense.Expense;
import com.payshare.server.model.expense.scheduled.Schedule;
import com.payshare.server.model.expense.scheduled.group.ScheduledGroupExpense;
import com.payshare.server.model.user.Person;
import lombok.*;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ScheduledOwnExpense extends Expense {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	private BigDecimal amount;

	private Currency currency;

	@ManyToOne(cascade = CascadeType.ALL)
	private Schedule schedule;

	@ManyToOne
	private Person person;

	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public Schedule getSchedule() {
		// TODO Auto-generated method stub
		return this.schedule;
	}

	public void setTitle(String title) {
		// TODO Auto-generated method stub
		this.title = title;
	}

	public void setSchedule(Schedule schedule2) {
		// TODO Auto-generated method stub
		this.schedule = schedule2;
	}

	public Person getPerson() {
		// TODO Auto-generated method stub
		return this.person;
	}

	public void setCurrency(Currency currency) {
		// TODO Auto-generated method stub
		this.currency = currency;
	}

	public void setPerson(Person person2) {
		// TODO Auto-generated method stub
		this.person = person2;
	}

	public void setAmount(BigDecimal amount) {
		// TODO Auto-generated method stub
		this.amount = amount;
	}

	public @NotBlank String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	public BigDecimal getAmount() {
		// TODO Auto-generated method stub
		return this.amount;
	}

	public Currency getCurrency() {
		// TODO Auto-generated method stub
		return this.currency;
	}

}
