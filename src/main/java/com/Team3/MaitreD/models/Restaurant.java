// Entity creates restaurants table in database to store restaurant specific data
package com.Team3.MaitreD.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.IncrementGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="restaurants")
public class Restaurant {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "seq")
	@GenericGenerator(name = "seq", type=IncrementGenerator.class)
	private Integer restaurant_id;
	@Column(unique=true)
	private String restaurantName;
	private String address;
	@Column(unique=true)
	private String phoneNumber;
	private String cuisine;
	private String openingTime;
	private String closingTime;
	private String photo;

	public Restaurant() {
		super();
	}

	public Integer getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Integer restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getAddress() {
		return address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}


}
