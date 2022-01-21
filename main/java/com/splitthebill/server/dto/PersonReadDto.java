package com.splitthebill.server.dto;

import com.splitthebill.server.model.Currency;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor
public class PersonReadDto extends RepresentationModel<PersonReadDto> {

    public PersonReadDto(Long id2, String name2, Map<Currency, BigDecimal> balancesM) {
    	this.id = id2;
    	this.name = name2;
    	this.balances = balancesM;
    }

	public Long id;
    public String name;
    public Map<Currency, BigDecimal> balances;
}
