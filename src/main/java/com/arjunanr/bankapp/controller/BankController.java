package com.arjunanr.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arjunanr.bankapp.dto.TransactionDTO;
import com.arjunanr.bankapp.dto.TransactionsDTO;
import com.arjunanr.bankapp.dto.UserDetailsDTO;
import com.arjunanr.bankapp.service.BankService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BankController {

	@Autowired
	BankService bankService;
	
	/**
	 * Fetch User and Account Details
	 * @param username
	 * @return
	 */
	@GetMapping("/user/{username}")
	public UserDetailsDTO fetchUserAndAccountDetails(@PathVariable String username) {
		return bankService.fetchUserAndAccountDetails(username);
	}

	/**
	 * Withdraw and Deposit Transaction based on Event
	 * @param username
	 * @param eventId
	 * @param transactionDTO
	 * @return
	 */
	@PutMapping("/user/{username}/events/{event-id}")
	public TransactionDTO makeTransaction(@PathVariable String username,@PathVariable("event-id") String eventId, @RequestBody TransactionDTO transactionDTO) {
		return bankService.makeTransaction(eventId, username, transactionDTO.getAmount());
	}
	
	/**
	 * Transaction History
	 * @param username
	 * @return
	 */
	@GetMapping("/user/{username}/transactions")
	public List<TransactionsDTO> fetchTransactions(@PathVariable String username) {
		return bankService.fetchTransactionList(username);
	}
}
