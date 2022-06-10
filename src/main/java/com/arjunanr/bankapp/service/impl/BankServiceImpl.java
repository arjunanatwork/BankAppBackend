package com.arjunanr.bankapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arjunanr.bankapp.domain.AccountEntity;
import com.arjunanr.bankapp.domain.TransactionEntity;
import com.arjunanr.bankapp.domain.UserEntity;
import com.arjunanr.bankapp.dto.TransactionDTO;
import com.arjunanr.bankapp.dto.TransactionsDTO;
import com.arjunanr.bankapp.dto.UserDetailsDTO;
import com.arjunanr.bankapp.expection.BusinessException;
import com.arjunanr.bankapp.repo.AccountRepo;
import com.arjunanr.bankapp.repo.TransactionRepo;
import com.arjunanr.bankapp.repo.UserRepo;

@Service
public class BankServiceImpl implements com.arjunanr.bankapp.service.BankService {

	private static final String DEPOSIT = "DEPOSIT";
	private static final String WITHDRAW = "WITHDRAW";
	
	@Autowired
	UserRepo authRepo;
	
	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	TransactionRepo transactionRepo;
	
	@Override
	public UserDetailsDTO fetchUserAndAccountDetails(String username) {
		
		UserEntity userIntance = authRepo.findByUsername(username);
		
		AccountEntity accountInstance = accountRepo.findByUser(userIntance);
		
		UserDetailsDTO userDetailDTO = new UserDetailsDTO();
		userDetailDTO.setName(userIntance.getName());
		userDetailDTO.setEmail(userIntance.getEmail());
		userDetailDTO.setUsername(userIntance.getUsername());
		userDetailDTO.setAccountNo(accountInstance.getAccountNo());
		userDetailDTO.setTotalBalance(accountInstance.getTotalBalance());
		
		return userDetailDTO;
	}

	@Override
	public TransactionDTO makeTransaction(String event, String username, double amount) {
		UserEntity userIntance = authRepo.findByUsername(username);

		AccountEntity accountInstance = accountRepo.findByUser(userIntance);
		
		TransactionEntity transactionInstance = new TransactionEntity();

		if(event.equalsIgnoreCase(DEPOSIT)) {
			accountInstance.setTotalBalance(accountInstance.getTotalBalance() + amount);
			transactionInstance.setDepositAmount(amount);
			transactionInstance.setTransactionType(DEPOSIT);
		} else if(event.equalsIgnoreCase(WITHDRAW)) {
			if(amount > accountInstance.getTotalBalance()) {
				throw new BusinessException("Insuffienct Balance");
			}
			accountInstance.setTotalBalance(accountInstance.getTotalBalance() - amount);
			transactionInstance.setWithdrawAmount(amount);
			transactionInstance.setTransactionType(WITHDRAW);
		}
		
		AccountEntity savedAccountInstance = accountRepo.save(accountInstance);
		
		// Create a Transaction Entry
		transactionInstance.setAccount(savedAccountInstance);
		transactionInstance.setBalance(savedAccountInstance.getTotalBalance());
		transactionInstance.setTransactionDate(new Date());
		transactionRepo.save(transactionInstance);
		
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setBalance(savedAccountInstance.getTotalBalance());
		transactionDTO.setAmount(amount);
		transactionDTO.setTransactionType(event.toUpperCase());
		
		return transactionDTO;
	}

	@Override
	public List<TransactionsDTO> fetchTransactionList(String username) {
		UserEntity userIntance = authRepo.findByUsername(username);
		AccountEntity accountInstance = accountRepo.findByUser(userIntance);
		
		List<TransactionEntity> transactionInstanceList = transactionRepo.findAllByAccountOrderByTransactionDateDesc(accountInstance);
		
		return transactionInstanceList.stream().map(this::toTransactionDTO).collect(Collectors.toList());
	}

	
	private TransactionsDTO toTransactionDTO(TransactionEntity entity) {
		TransactionsDTO transactionDTO = new TransactionsDTO();
		SimpleDateFormat dateTimeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		transactionDTO.setId(entity.getId());
		transactionDTO.setDepositAmount(entity.getDepositAmount());
		transactionDTO.setWithdrawAmount(entity.getWithdrawAmount());
		transactionDTO.setBalance(entity.getBalance());
		transactionDTO.setTransactionDate(dateTimeformat.format(entity.getTransactionDate()));
		transactionDTO.setTransactionType(entity.getTransactionType());
		return transactionDTO;
		
	}
}
