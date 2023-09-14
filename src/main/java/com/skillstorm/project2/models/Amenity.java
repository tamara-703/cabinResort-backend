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
	private boolean petFriendly;
	
	@Column(name="patio")
	private boolean patio;
	
	@Column(name="fireplace")
	private boolean fireplace;
	
	@Column(name="kitchen")
	private boolean kitchen;
	
	@Column(name="jacuzzi")
	private boolean jacuzzi;
	
	@Column(name="outdoor_hot_shower")
	private boolean outdoorHotShower;
	
	@Column(name="outdoor_furniture")
	private boolean outdoorFurniture;

	public Amenity() {}

	public Amenity(long id, boolean pet_friendly, boolean patio, boolean fireplace, boolean kitchen, boolean jacuzzi,
			boolean outdoor_hot_shower, boolean outdoor_furniture) {
		super();
		this.id = id;
		this.petFriendly = pet_friendly;
		this.patio = patio;
		this.fireplace = fireplace;
		this.kitchen = kitchen;
		this.jacuzzi = jacuzzi;
		this.outdoorHotShower = outdoor_hot_shower;
		this.outdoorFurniture = outdoor_furniture;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isPet_friendly() {
		return petFriendly;
	}

	public void setPet_friendly(boolean pet_friendly) {
		this.petFriendly = pet_friendly;
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
		return outdoorHotShower;
	}

	public void setOutdoor_hot_shower(boolean outdoor_hot_shower) {
		this.outdoorHotShower = outdoor_hot_shower;
	}

	public boolean isOutdoor_furniture() {
		return outdoorFurniture;
	}

	public void setOutdoor_furniture(boolean outdoor_furniture) {
		this.outdoorFurniture = outdoor_furniture;
	}

}
