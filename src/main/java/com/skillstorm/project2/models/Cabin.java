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
	private Amenity amenities_id;
	
	@OneToOne
	@JoinColumn(name="image_id")
	private Image image_id;

	public Cabin() {}

	public Cabin(long id, String cabin_name, int no_rooms, int no_bathrooms, int sleeps, double price,
			String description, int capacity, Amenity amenities_id, Image image_id) {
		super();
		this.id = id;
		this.cabin_name = cabin_name;
		this.no_rooms = no_rooms;
		this.no_bathrooms = no_bathrooms;
		this.sleeps = sleeps;
		this.price = price;
		this.description = description;
		this.capacity = capacity;
		this.amenities_id = amenities_id;
		this.image_id = image_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCabin_name() {
		return cabin_name;
	}

	public void setCabin_name(String cabin_name) {
		this.cabin_name = cabin_name;
	}

	public int getNo_rooms() {
		return no_rooms;
	}

	public void setNo_rooms(int no_rooms) {
		this.no_rooms = no_rooms;
	}

	public int getNo_bathrooms() {
		return no_bathrooms;
	}

	public void setNo_bathrooms(int no_bathrooms) {
		this.no_bathrooms = no_bathrooms;
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
		return amenities_id;
	}

	public void setAmenities_id(Amenity amenities_id) {
		this.amenities_id = amenities_id;
	}

	public Image getImage_id() {
		return image_id;
	}

	public void setImage_id(Image image_id) {
		this.image_id = image_id;
	}

}
