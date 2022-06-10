package com.arjunanr.bankapp.dto;

import lombok.Data;

@Data
public class UserDetailsDTO {

	private String name;
	private String username;
	private String email;
	private int accountNo;
	private double totalBalance;
	
}
