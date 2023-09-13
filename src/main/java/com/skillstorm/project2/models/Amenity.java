package com.skillstorm.project2.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

}
