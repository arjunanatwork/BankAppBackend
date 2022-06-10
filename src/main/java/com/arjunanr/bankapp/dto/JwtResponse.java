package com.arjunanr.bankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

	private final String token;
	private String username;

}
