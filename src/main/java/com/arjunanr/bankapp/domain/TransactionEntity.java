package com.arjunanr.bankapp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TRANSACTION")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class TransactionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_generator")
	@SequenceGenerator(name="transaction_generator", sequenceName = "transaction_seq", allocationSize=1)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private AccountEntity account;
	
	@Column(name = "withdraw_amount")
	private double withdrawAmount;
	
	@Column(name = "deposit_amount")
	private double depositAmount;
	
	@Column(name = "balance")
	private double balance;
	
	@Column(name = "transaction_type")
	private String transactionType;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "transaction_date")
	private Date transactionDate;
}
