package com.payshare.server.model.expense;

import com.payshare.server.model.user.PersonGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PersonGroupExpense {

	@Id
	@GeneratedValue
	private Long id;

	@Min(0)
	@Max(10)
	private int weight;

	private boolean isReviewed = false;

	@ManyToOne
	private PersonGroup debtor;

	@ManyToOne
	private GroupExpense expense;

	public PersonGroupExpense(int weight, PersonGroup debtor, GroupExpense expense) {
		this.weight = weight;
		this.debtor = debtor;
		this.expense = expense;
	}

	public PersonGroup getDebtor() {
		// TODO Auto-generated method stub
		return this.getDebtor();
	}

	public void setWeight(int i) {
		// TODO Auto-generated method stub

	}

	public void setDebtor(PersonGroup receiver) {
		// TODO Auto-generated method stub

	}

	public void setExpense(GroupExpense groupExpense) {
		// TODO Auto-generated method stub

	}

	public long getWeight() {
		// TODO Auto-generated method stub
		return this.weight;
	}
}
