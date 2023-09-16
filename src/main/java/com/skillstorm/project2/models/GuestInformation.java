package com.skillstorm.project2.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/*
 * TODO : Add @NotBlank annotation to all columns except Id (since all columns will be required)
 */

@Entity
@Table(name="guests")
public class GuestInformation implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="last_name", length=255)
	private String lastName;
	
	@Column(name="first_name", length=255)
	private String firstName;
	
	@Column(name="username", length=255)
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email", length=255)
	private String email;
	
	@Column(name="phone", length=255)
	private String phone;
	
	@Column(name="address", length=255)
	private String address;
	
	@Column(name="language", length=255)
	private String language;
	
	@Column(name="role", length=255)
	private String role;

	public GuestInformation() {}

	public GuestInformation(long id, String last_name, String first_name, String username, String password,
			String email, String phone, String address, String language, String role) {
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
		this.role = role;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
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
	
	public String getRole()
	{
		return role;
	}
	
	public void setRole(String role)
	{
		this.role = role;
	}

	@Override
	public String toString() {
		return "GuestInformation [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", username="
				+ username + ", password=" + password + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", language=" + language + ", role=" + role + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		SimpleGrantedAuthority userRole = new SimpleGrantedAuthority(role);
		authorities.add(userRole);
		
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	

}
