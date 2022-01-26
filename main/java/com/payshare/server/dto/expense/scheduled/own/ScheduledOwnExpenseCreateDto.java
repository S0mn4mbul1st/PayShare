package com.payshare.server.dto.expense.scheduled.own;

import com.payshare.server.dto.expense.scheduled.ScheduleReadDto;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public class ScheduledOwnExpenseCreateDto {

    public String title;
    public BigDecimal amount;
    public String currency;
    public ScheduleReadDto schedule;

}
