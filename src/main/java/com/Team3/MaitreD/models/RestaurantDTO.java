
package com.Team3.MaitreD.models;

public class RestaurantDTO {
	private String restaurantName;
	private String address;
	private String phoneNumber;
	private String cuisine;
	private String openingTime;
	private String closingTime;
	private String imageUri; // URI to the restaurant image

	public RestaurantDTO() {
		super();
	}
	public RestaurantDTO(String restaurantName, String address, String phoneNumber, String cuisine,
						 String openingTime, String closingTime, String imageUri) {
		this.restaurantName = restaurantName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.cuisine = cuisine;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.imageUri = imageUri; // Initialize image URI
	}

	// Getter and setter for imageUri
	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	// Existing getters and setters...
}
