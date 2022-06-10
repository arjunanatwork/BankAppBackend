package com.arjunanr.bankapp.dto;

import lombok.Data;

@Data
public class TransactionDTO {

	double amount;
	String transactionType;
	double balance;
	
}
