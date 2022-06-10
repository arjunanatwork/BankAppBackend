package com.arjunanr.bankapp.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ACCOUNT")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class AccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
	@SequenceGenerator(name="account_generator", sequenceName = "account_seq", allocationSize=1)
	private int id;
	
	@Column(name = "account_no")
	private int accountNo;

	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
	private UserEntity user;
	
	@Column(name = "total_balance")
	private double totalBalance;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<TransactionEntity> transactions;
}
