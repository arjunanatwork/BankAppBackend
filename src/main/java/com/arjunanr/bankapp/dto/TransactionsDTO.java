package com.arjunanr.bankapp.dto;

import lombok.Data;

@Data
public class TransactionsDTO {

	private int id;
	private double withdrawAmount;
	private double depositAmount;
	private String transactionDate;
	private String transactionType;
	private double balance;
	
}
