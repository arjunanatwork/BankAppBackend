package com.arjunanr.bankapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjunanr.bankapp.domain.AccountEntity;
import com.arjunanr.bankapp.domain.TransactionEntity;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionEntity, Long> {

	List<TransactionEntity> findAllByAccountOrderByTransactionDateDesc(AccountEntity account);
}
