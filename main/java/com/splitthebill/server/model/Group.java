package com.splitthebill.server.model;

import com.splitthebill.server.model.expense.GroupExpense;
import com.splitthebill.server.model.expense.PersonGroupExpense;
import com.splitthebill.server.model.user.PersonGroup;
import com.splitthebill.server.model.user.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`group`")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UserAccount userAccount;
    
    @Column(name = "`name`")
    private String name;

    private String photoPath;

    //TODO? boolean isSettledUp

    @OneToMany(mappedBy = "group", cascade = CascadeType.PERSIST)
    private List<PersonGroup> members = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private List<GroupExpense> expenses = new ArrayList<>();

    public void addExpense(GroupExpense expense) {
        expenses.add(expense);
        BigDecimal amount = expense.getAmount();
        Currency currency = expense.getCurrency();
        PersonGroup creditor = expense.getCreditor();
        creditor.addToBalance(currency, amount);
        BigDecimal totalWeight = BigDecimal.valueOf(expense.getTotalWeight());
        BigDecimal amountLeft = amount;
        List<PersonGroupExpense> participants = expense.getPersonGroupExpenses();
        for (PersonGroupExpense participant : participants) {
            BigDecimal splitRatio = BigDecimal.valueOf(participant.getWeight())
                    .divide(totalWeight, 4, RoundingMode.HALF_UP);
            BigDecimal toSubtract = amount.multiply(splitRatio).setScale(2, RoundingMode.HALF_UP);
            participant.getDebtor().subtractFromBalance(currency, toSubtract);
            amountLeft = amountLeft.subtract(toSubtract);
        }
        if (!amountLeft.equals(BigDecimal.ZERO))
            creditor.addToBalance(currency, amountLeft.negate());
    }

    public void deleteExpense(GroupExpense expense) {
        expenses.remove(expense);
        PersonGroup creditor = expense.getCreditor();
        BigDecimal amount = expense.getAmount();
        Currency currency = expense.getCurrency();
        creditor.addToBalance(currency, amount);
        BigDecimal totalWeight = BigDecimal.valueOf(expense.getTotalWeight());
        BigDecimal amountLeft = amount;
        List<PersonGroupExpense> participants = expense.getPersonGroupExpenses();
        for (PersonGroupExpense participant : participants){
            BigDecimal splitRatio = BigDecimal.valueOf(participant.getWeight())
                    .divide(totalWeight, 4, RoundingMode.HALF_UP);
            BigDecimal toRecover = amount.multiply(splitRatio).setScale(2, RoundingMode.HALF_UP);
            participant.getDebtor().addToBalance(currency, toRecover);
            amountLeft = amountLeft.add(toRecover);
        }
        if (!amountLeft.equals(BigDecimal.ZERO))
            creditor.addToBalance(currency, amountLeft.negate());
    }

    public void addMember(PersonGroup member) {
        members.add(member);
    }

	public Collection<GroupExpense> getMembers() {
		// TODO Auto-generated method stub
		return this.getMembers();
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public void setName(@NotBlank @Size(min = 2) String name2) {
		// TODO Auto-generated method stub
		this.name = name2;
	}

	public void setMembers(LinkedList<PersonGroup> members2) {
		// TODO Auto-generated method stub
		this.members = members2;
	}

	public UserAccount getUserAccount() {
		// TODO Auto-generated method stub
		return this.userAccount;
	}

}
