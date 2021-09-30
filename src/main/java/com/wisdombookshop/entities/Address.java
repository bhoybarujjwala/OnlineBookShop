package com.wisdombookshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Id
	 private int userId;
	 private int flatNo;
	 private String streetName;
	 private String location;
	 private String city;
	 private String state;
	public Address() {
		super();
	}
	
	public Address(int userId, int flatNo, String streetName, String location, String city, String state) {
		this.userId = userId;
		this.flatNo = flatNo;
		this.streetName = streetName;
		this.location = location;
		this.city = city;
		this.state = state;
	}



	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFlatNo() {
		return flatNo;
	}
	public void setFlatNo(int flatNo) {
		this.flatNo = flatNo;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Address [userId=" + userId + ", flatNo=" + flatNo + ", streetName=" + streetName + ", location="
				+ location + ", city=" + city + ", state=" + state + "]";
	}
	
	
	 
}
