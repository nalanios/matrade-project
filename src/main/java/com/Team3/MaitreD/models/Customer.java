// Creates Customer table in database
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
@Table(name="customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "seq")
	@GenericGenerator(name = "seq", type=IncrementGenerator.class)
	private Integer customer_id;
	private String firstName;
	private String lastName;
	@Column(unique=true)
	private String phoneNumber;
	
	

	public Customer() {
		super();
	}



	public Integer getCustomer_id() {
		return customer_id;
	}



	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
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
