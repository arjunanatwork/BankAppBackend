package com.arjunanr.bankapp.service;

import java.util.List;

import com.arjunanr.bankapp.dto.TransactionDTO;
import com.arjunanr.bankapp.dto.TransactionsDTO;
import com.arjunanr.bankapp.dto.UserDetailsDTO;

public interface BankService {

	UserDetailsDTO fetchUserAndAccountDetails(String username);
	
	TransactionDTO makeTransaction(String event, String username, double amount);
	
	List<TransactionsDTO> fetchTransactionList(String username);

}
