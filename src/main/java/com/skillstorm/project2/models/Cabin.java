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
@Table(name="cabins")
public class Cabin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="cabin_name")
	private String cabin_name;
	
	@Column(name="no_rooms")
	private int no_rooms;
	
	@Column(name="no_bathrooms")
	private int no_bathrooms;
	
	@Column(name="price")
	private double price;
	
	@OneToOne
	@JoinColumn(name="amenities_id")
	private Amenity amenities_id;

}
