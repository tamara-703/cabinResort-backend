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
@Table(name="amenities")
public class Amenity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="pet_friendly")
	private boolean pet_friendly;
	
	@Column(name="patio")
	private boolean patio;
	
	@Column(name="fireplace")
	private boolean fireplace;
	
	@Column(name="kitchen")
	private boolean kitchen;
	
	@Column(name="jacuzzi")
	private boolean jacuzzi;
	
	@Column(name="outdoor_hot_shower")
	private boolean outdoor_hot_shower;
	
	@Column(name="outdoor_furniture")
	private boolean outdoor_furniture;

	public Amenity() {}

	public Amenity(long id, boolean pet_friendly, boolean patio, boolean fireplace, boolean kitchen, boolean jacuzzi,
			boolean outdoor_hot_shower, boolean outdoor_furniture) {
		super();
		this.id = id;
		this.pet_friendly = pet_friendly;
		this.patio = patio;
		this.fireplace = fireplace;
		this.kitchen = kitchen;
		this.jacuzzi = jacuzzi;
		this.outdoor_hot_shower = outdoor_hot_shower;
		this.outdoor_furniture = outdoor_furniture;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isPet_friendly() {
		return pet_friendly;
	}

	public void setPet_friendly(boolean pet_friendly) {
		this.pet_friendly = pet_friendly;
	}

	public boolean isPatio() {
		return patio;
	}

	public void setPatio(boolean patio) {
		this.patio = patio;
	}

	public boolean isFireplace() {
		return fireplace;
	}

	public void setFireplace(boolean fireplace) {
		this.fireplace = fireplace;
	}

	public boolean isKitchen() {
		return kitchen;
	}

	public void setKitchen(boolean kitchen) {
		this.kitchen = kitchen;
	}

	public boolean isJacuzzi() {
		return jacuzzi;
	}

	public void setJacuzzi(boolean jacuzzi) {
		this.jacuzzi = jacuzzi;
	}

	public boolean isOutdoor_hot_shower() {
		return outdoor_hot_shower;
	}

	public void setOutdoor_hot_shower(boolean outdoor_hot_shower) {
		this.outdoor_hot_shower = outdoor_hot_shower;
	}

	public boolean isOutdoor_furniture() {
		return outdoor_furniture;
	}

	public void setOutdoor_furniture(boolean outdoor_furniture) {
		this.outdoor_furniture = outdoor_furniture;
	}

}
