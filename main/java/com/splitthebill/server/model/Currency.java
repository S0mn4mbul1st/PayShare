package com.splitthebill.server.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique = true)
    @ToString.Include
    private String abbreviation;

    @Column(precision = 19, scale = 9)
    @NonNull
    private BigDecimal exchangeRate;

	private String key;

    public Currency(String key, BigDecimal value) {
		// TODO Auto-generated constructor stub
    	this.key = key;
    	this.exchangeRate = value;
    }

	@Override
    public String toString() {
        return abbreviation;
    }

	public @NotEmpty String getAbbreviation() {
		// TODO Auto-generated method stub
		return this.abbreviation;
	}

	public void setExchangeRate(BigDecimal value) {
		// TODO Auto-generated method stub
		this.exchangeRate = value;
	}
}
