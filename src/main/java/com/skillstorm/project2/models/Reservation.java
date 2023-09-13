package com.skillstorm.project2.models;

import java.util.Date;

import javax.persistence.Column;
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

	@Column(name="check_in")
	private Date check_in;
	
	@Column(name="check_out")
	private Date check_out;
	
	
	@OneToOne
	@JoinColumn(name="reserved_cabin_id")
	private Cabin reserved_cabin_id;
	
	@OneToOne
	@JoinColumn(name="guest_id")
	private GuestInformation guest_id;

}
