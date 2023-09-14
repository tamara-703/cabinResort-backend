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
	private String lastName;
	
	@Column(name="first_name")
	private String firstName;
	
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

	public GuestInformation() {}

	public GuestInformation(long id, String last_name, String first_name, String username, String password,
			String email, int phone, String address, String language) {
		super();
		this.id = id;
		this.lastName = last_name;
		this.firstName = first_name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.language = language;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLast_name() {
		return lastName;
	}

	public void setLast_name(String last_name) {
		this.lastName = last_name;
	}

	public String getFirst_name() {
		return firstName;
	}

	public void setFirst_name(String first_name) {
		this.firstName = first_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "GuestInformation [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", username="
				+ username + ", password=" + password + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", language=" + language + "]";
	}

}
