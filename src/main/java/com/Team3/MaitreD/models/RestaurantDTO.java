//Data transfer object for registering new restaurants and sending restaurant info to client side
package com.Team3.MaitreD.models;

public class RestaurantDTO {
	private String restaurantName;
	private String address;
	private String phoneNumber;
	private String cuisine;
	private String openingTime;
	private String closingTime;
	
	public RestaurantDTO() {
		super();
	}
	public RestaurantDTO(String restaurantName, String address, String phoneNumber, String cuisine,
			String openingTime, String closingTime) {
		this.restaurantName = restaurantName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.cuisine = cuisine;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
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
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
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
	
	
}
