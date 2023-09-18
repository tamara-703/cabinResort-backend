package com.skillstorm.project2.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="cabins")
public class Cabin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="cabin_name")
	private String cabinName;
	
	@Column(name="no_rooms")
	private int noRooms;
	
	@Column(name="no_bathrooms")
	private int noBathrooms;
	
	@Column(name="sleeps")
	private int sleeps;
	
	@Column(name="price")
	private double price;
	
	@Column(name="description")
	private String description;
	
	@Column(name="capacity")
	private int capacity;
	
	@OneToOne
	@JoinColumn(name="amenities_id")
	private Amenity amenitiesId;
	
	@OneToOne
	@JoinColumn(name="image_id")
	private Image imageId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="stateid", nullable=false)
	@JsonIgnore
	@JsonBackReference
	private CabinLocations cabinloc;
	

	public Cabin() {}

	public Cabin(long id, String cabin_name, int no_rooms, int no_bathrooms, int sleeps, double price,
			String description, int capacity, Amenity amenities_id, Image image_id, CabinLocations cabinloc) {
		super();
		this.id = id;
		this.cabinName = cabin_name;
		this.noRooms = no_rooms;
		this.noBathrooms = no_bathrooms;
		this.sleeps = sleeps;
		this.price = price;
		this.description = description;
		this.capacity = capacity;
		this.amenitiesId = amenities_id;
		this.imageId = image_id;
		this.cabinloc = cabinloc;
	}

	
	public CabinLocations getCabinloc() {
		return cabinloc;
	}

	public void setCabinloc(CabinLocations cabinloc) {
		this.cabinloc = cabinloc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCabin_name() {
		return cabinName;
	}

	public void setCabin_name(String cabin_name) {
		this.cabinName = cabin_name;
	}

	public int getNo_rooms() {
		return noRooms;
	}

	public void setNo_rooms(int no_rooms) {
		this.noRooms = no_rooms;
	}

	public int getNo_bathrooms() {
		return noBathrooms;
	}

	public void setNo_bathrooms(int no_bathrooms) {
		this.noBathrooms = no_bathrooms;
	}

	public int getSleeps() {
		return sleeps;
	}

	public void setSleeps(int sleeps) {
		this.sleeps = sleeps;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Amenity getAmenities_id() {
		return amenitiesId;
	}

	public void setAmenities_id(Amenity amenities_id) {
		this.amenitiesId = amenities_id;
	}

	public Image getImage_id() {
		return imageId;
	}

	public void setImage_id(Image image_id) {
		this.imageId = image_id;
	}

}
