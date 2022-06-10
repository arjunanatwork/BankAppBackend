package com.arjunanr.bankapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjunanr.bankapp.domain.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long>{

	UserEntity findByUsername(String username);
}
