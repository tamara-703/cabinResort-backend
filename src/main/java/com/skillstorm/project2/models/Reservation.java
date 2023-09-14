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
	private Date checkIn;
	
	@Column(name="check_out")
	private Date checkOut;
	
	
	@OneToOne
	@JoinColumn(name="reserved_cabin_id")
	private Cabin reservedCabinId;
	
	@OneToOne
	@JoinColumn(name="guest_id")
	private GuestInformation guestId;

	public Reservation() {}

	public Reservation(long id, Date check_in, Date check_out, Cabin reserved_cabin_id, GuestInformation guest_id) {
		super();
		this.id = id;
		this.checkIn = check_in;
		this.checkOut = check_out;
		this.reservedCabinId = reserved_cabin_id;
		this.guestId = guest_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCheck_in() {
		return checkIn;
	}

	public void setCheck_in(Date check_in) {
		this.checkIn = check_in;
	}

	public Date getCheck_out() {
		return checkOut;
	}

	public void setCheck_out(Date check_out) {
		this.checkOut = check_out;
	}

	public Cabin getReserved_cabin_id() {
		return reservedCabinId;
	}

	public void setReserved_cabin_id(Cabin reserved_cabin_id) {
		this.reservedCabinId = reserved_cabin_id;
	}

	public GuestInformation getGuest_id() {
		return guestId;
	}

	public void setGuest_id(GuestInformation guest_id) {
		this.guestId = guest_id;
	}

}
