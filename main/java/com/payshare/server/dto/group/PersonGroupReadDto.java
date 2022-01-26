package com.payshare.server.dto.group;

import com.payshare.server.model.Currency;
import com.payshare.server.model.user.PersonGroup;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class PersonGroupReadDto {

    Long groupId;
    Long groupMemberId;
    Long personId;
    String name;
    Map<Currency, BigDecimal> memberBalance;

    public PersonGroupReadDto(PersonGroup member) {
        this.groupId = member.getGroup().getId();
        this.groupMemberId = member.getId();
        this.personId = member.getPerson().getId();
        this.name = member.getPerson().getName();
        this.memberBalance = member.getBalances();
    }
}
