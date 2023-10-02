package com.skillstorm.project2.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity

public class CabinLocations {
	
	@Id
	@Column(name = "stateid")
	private String stateId;
	
	@Column
	private String address;
	
	@Column
	private String city;
	
	@Column
	private String zip;
	
	@JsonBackReference
	@OneToMany(mappedBy = "cabinloc", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Cabin> cabins;
	
	public CabinLocations() {}

	public CabinLocations(String stateId, String address, String city, String zip, List<Cabin> cabins) {
		super();
		this.stateId = stateId;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.cabins = cabins;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<Cabin> getCabins() {
		return cabins;
	}

	public void setCabins(List<Cabin> cabins) {
		this.cabins = cabins;
	}

	@Override
	public String toString() {
		return "CabinLocations [stateId=" + stateId + ", address=" + address + ", city=" + city + ", zip=" + zip
				+ ", cabins=" + cabins + "]";
	}
	
		
}
