package com.arjunanr.bankapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.arjunanr.bankapp.domain.UserEntity;
import com.arjunanr.bankapp.dto.UserDTO;

public interface JwtUserDetailsService extends UserDetailsService {

	UserEntity registerUser(UserDTO user);
}
