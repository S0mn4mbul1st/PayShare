package com.payshare.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class LoginRequestDto {

	@Pattern(regexp = "[a-z0-9]{4,15}", message = "Should contain only lowercase letters or numbers and be 4-15 characters long.")
	public String username;

	@NotBlank
	public String password;

	public LoginRequestDto(
			@Pattern(regexp = "[a-z0-9]{4,15}", message = "Should contain only lowercase letters or numbers and be 4-15 characters long.") String username2,
			String unHashedPassword) {
		this.username = username2;
		this.password = unHashedPassword;
	}

	public Object getUsername() {
		return this.username;
	}

	public Object getPassword() {
		return this.password;
	}
}
