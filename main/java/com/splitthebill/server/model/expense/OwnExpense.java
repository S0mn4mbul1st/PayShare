package com.splitthebill.server.model.expense;

import com.splitthebill.server.model.Currency;
import com.splitthebill.server.model.user.Person;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OwnExpense extends Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Currency currency;
    
    @ManyToOne
    private Person owner;

	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public String getReceiptPhoto() {
		// TODO Auto-generated method stub
		return this.getReceiptPhoto();
	}

	public void setCurrency(Currency currency) {
		// TODO Auto-generated method stub
		this.currency = currency;
	}

	public void setOwner(Person person) {
		// TODO Auto-generated method stub
		this.owner = person;
	}

}
