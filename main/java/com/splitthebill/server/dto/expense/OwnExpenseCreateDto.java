package com.splitthebill.server.dto.expense;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
public class OwnExpenseCreateDto {

    @NotBlank
    String title;

    @DecimalMin("0.01")
    BigDecimal amount;

    String receiptPhoto;

    @NotEmpty
    String currencyAbbreviation;

	public String getCurrencyAbbreviation() {
		// TODO Auto-generated method stub
		return this.currencyAbbreviation;
	}

	public Object getAmount() {
		// TODO Auto-generated method stub
		return this.amount;
	}

	public Object getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	public Object getReceiptPhoto() {
		// TODO Auto-generated method stub
		return this.receiptPhoto;
	}

	public void setTitle(@NotBlank String title2) {
		// TODO Auto-generated method stub
		this.title = title2;
	}

	public void setAmount(BigDecimal amount2) {
		// TODO Auto-generated method stub
		this.amount = amount2;
	}

	public void setCurrencyAbbreviation(@NotEmpty String abbreviation) {
		// TODO Auto-generated method stub
		this.currencyAbbreviation = abbreviation;
	}

}