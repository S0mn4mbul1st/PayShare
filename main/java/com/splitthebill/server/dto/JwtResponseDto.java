package com.splitthebill.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDto {
    public JwtResponseDto(String jwt) {
			this.token = jwt;
	}
	private String token;
    private final String type = "Bearer";
}
