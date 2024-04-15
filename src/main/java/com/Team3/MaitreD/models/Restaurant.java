package com.Team3.MaitreD.models;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="restaurants")
public class Restaurant {

	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer restaurant_id;
	@Column(unique=true)
	private String username;
	private String password;
	@Column(unique=true)
	private String email;
	@Column(unique=true)
	private String restaurantName;
	private String address;
	
//	@ManyToMany(fetch=FetchType.EAGER)
//	@JoinTable(
//	        name="restaurant_role_junction",
//	        joinColumns = {@JoinColumn(name="restaurant_id")},
//	        inverseJoinColumns = {@JoinColumn(name="role_id")}
//	    )
	//private Set<Role> authorities;
	
	
	public Restaurant(Integer restaurant_id, String username, String password, String email) {
		super();
		this.restaurant_id = restaurant_id;
		this.username = username;
		this.password = password;
		this.email = email;
	//	this.authorities = authorities;
	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return this.authorities;
//	}
	
	

	public Integer getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Integer restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public void setAuthorities(Set<Role> authorities) {
//		this.authorities = authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		return this.password;
//	}
//
//	@Override
//	public String getUsername() {
//		return this.username;
//	}


}
