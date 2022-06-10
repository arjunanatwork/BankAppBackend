package com.arjunanr.bankapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjunanr.bankapp.domain.AccountEntity;
import com.arjunanr.bankapp.domain.UserEntity;

@Repository
public interface AccountRepo extends JpaRepository<AccountEntity, Long> {

	AccountEntity findByUser(UserEntity userInstance);
}
