package com.arjunanr.bankapp.service.impl;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arjunanr.bankapp.domain.AccountEntity;
import com.arjunanr.bankapp.domain.UserEntity;
import com.arjunanr.bankapp.dto.UserDTO;
import com.arjunanr.bankapp.repo.AccountRepo;
import com.arjunanr.bankapp.repo.UserRepo;
import com.arjunanr.bankapp.service.JwtUserDetailsService;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {

	@Autowired
	UserRepo authRepo;
	
	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity userInstance = authRepo.findByUsername(username);
		if (userInstance == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new User(userInstance.getUsername(), userInstance.getPassword(),
				new ArrayList<>());
	}
	
	@Override
	public UserEntity registerUser(UserDTO user) {
		// Save the New User
		UserEntity userInstance = new UserEntity();
		userInstance.setName(user.getName());
		userInstance.setUsername(user.getUsername());
		userInstance.setEmail(user.getEmail());
		userInstance.setPassword(bcryptEncoder.encode(user.getPassword()));
		UserEntity savedInstance = authRepo.save(userInstance);
		 
		// Save Account Details
		AccountEntity accountInstance = new AccountEntity();
		accountInstance.setAccountNo(ThreadLocalRandom.current().nextInt(100000, 1000000));
		accountInstance.setUser(savedInstance);
		accountInstance.setTotalBalance(0);
		
		accountRepo.save(accountInstance);
		
		return savedInstance;
	}
	
	

}
