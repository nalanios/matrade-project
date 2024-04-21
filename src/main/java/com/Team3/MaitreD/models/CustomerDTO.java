//Data transfer object for registering new customers and sending customer info to client side
package com.Team3.MaitreD.models;

public class CustomerDTO {

	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	public CustomerDTO() {
		super();
	}
	public CustomerDTO(String firstName, String lastName, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
