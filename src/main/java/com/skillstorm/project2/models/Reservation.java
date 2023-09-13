package com.skillstorm.project2.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="reservations")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private GuestInformation user_id;
	
	@OneToOne
	@JoinColumn(name="reserved_cabin_id")
	private Reservation reserved_cabin_id;
	
	private Date check_in;
	
	private Date check_out;

}
