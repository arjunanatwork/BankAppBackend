package com.arjunanr.bankapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="BANKUSER")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name="user_generator", sequenceName = "user_seq", allocationSize=1)
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
}
