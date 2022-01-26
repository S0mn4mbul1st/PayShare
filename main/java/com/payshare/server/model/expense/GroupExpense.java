package com.payshare.server.model.expense;

import com.payshare.server.model.Currency;
import com.payshare.server.model.Group;
import com.payshare.server.model.user.PersonGroup;
import lombok.*;
import org.hibernate.validator.cfg.context.Cascadable;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
public class GroupExpense extends Expense {

	@Id
	@GeneratedValue
	private Long id;

	private BigDecimal amount;
	@ManyToOne
	private Group group;

	Currency currency;

	@NotBlank
	public String title;

	@ManyToOne
	private PersonGroup creditor;

	@OneToMany(mappedBy = "expense", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private List<PersonGroupExpense> personGroupExpenses;

	public int getTotalWeight() {
		return personGroupExpenses.stream()
				.map(getTotalWeight())
				.reduce(0, Integer::sum);
	}

	public void setGroup(Group group2) {
		this.group = group2;
	}

	public void setPersonGroupExpenses(List<PersonGroupExpense> of) {
		this.personGroupExpenses = of;

	}

	public BigDecimal getAmount() {
		// TODO Auto-generated method stub
		return this.amount;
	}

	public Currency getCurrency() {
		// TODO Auto-generated method stub
		return this.currency;
	}

	public PersonGroup getCreditor() {
		// TODO Auto-generated method stub
		return this.creditor;
	}

	public List<PersonGroupExpense> getPersonGroupExpenses() {
		// TODO Auto-generated method stub
		return this.personGroupExpenses;
	}

	public @NotBlank String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	public void setTitle(String string) {
		this.title = string;
	}

	public void setCurrency(Currency currency2) {
		this.currency = currency2;

	}

	public void setAmount(BigDecimal amountDue) {
		this.amount = amountDue;

	}

	public void setCreditor(PersonGroup creditor2) {
		// TODO Auto-generated method stub
		this.creditor = creditor2;
	}

	public Group getGroup() {
		// TODO Auto-generated method stub
		return this.group;
	}

}
