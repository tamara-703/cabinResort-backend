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

	public Reservation() {}

	public Reservation(long id, Date check_in, Date check_out, Cabin reserved_cabin_id, GuestInformation guest_id) {
		super();
		this.id = id;
		this.check_in = check_in;
		this.check_out = check_out;
		this.reserved_cabin_id = reserved_cabin_id;
		this.guest_id = guest_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCheck_in() {
		return check_in;
	}

	public void setCheck_in(Date check_in) {
		this.check_in = check_in;
	}

	public Date getCheck_out() {
		return check_out;
	}

	public void setCheck_out(Date check_out) {
		this.check_out = check_out;
	}

	public Cabin getReserved_cabin_id() {
		return reserved_cabin_id;
	}

	public void setReserved_cabin_id(Cabin reserved_cabin_id) {
		this.reserved_cabin_id = reserved_cabin_id;
	}

	public GuestInformation getGuest_id() {
		return guest_id;
	}

	public void setGuest_id(GuestInformation guest_id) {
		this.guest_id = guest_id;
	}

}
