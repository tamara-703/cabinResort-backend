package com.skillstorm.project2.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="guests")
public class GuestInformation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="last_name")
	private String last_name;
	
	@Column(name="first_name")
	private String first_name;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone")
	private int phone;
	
	@Column(name="address")
	private String address;
	
	@Column(name="language")
	private String language;

}
