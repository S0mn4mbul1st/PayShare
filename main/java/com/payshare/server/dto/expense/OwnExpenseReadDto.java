package com.payshare.server.dto.expense;

import com.payshare.server.model.expense.OwnExpense;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class OwnExpenseReadDto {
    Long ownExpenseId;
    String title;
    BigDecimal amount;
    LocalDateTime created;
    String receiptPhoto;
    String currencyAbbreviation;

    public OwnExpenseReadDto(OwnExpense ownExpense) {
        this.ownExpenseId = ownExpense.getId();
        this.receiptPhoto = ownExpense.getReceiptPhoto();
    }
}