
package com.Team3.MaitreD.models;

public class CustomerDTO {
	private Integer customerID;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String imagePath; // Path to the customer image

	public CustomerDTO() {
		super();
	}

	public CustomerDTO(Integer customerID, String firstName, String lastName, String email, String phoneNumber, String imagePath) {
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.imagePath = imagePath; // Initialize image path
	}

	// Getter and setter for imagePath
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	// Existing getters and setters...
}
