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
@Table(name="customers")
public class Customer {
	
	private static final long serialVersionUID = 1L; //I dont know what this is but Spring says I need it
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer customer_id;
	@Column(unique=true)
	private String username;
	private String password;
	@Column(unique=true)
	private String email;
	
	
//	@ManyToMany(fetch=FetchType.EAGER)
//	@JoinTable(
//	        name="user_role_junction",
//	        joinColumns = {@JoinColumn(name="customer_id")},
//	        inverseJoinColumns = {@JoinColumn(name="role_id")}
//	    )
	//private Set<Role> authorities;
	
	public Customer() {
		
//		super();
//		this.customer_id = user_id;
//		this.username = username;
//		this.password = password;
//		this.email = email;
		//this.authorities = authorities;
	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return this.authorities;
//	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	@Override
//	public String getPassword() {
//		
//		return this.password;
//	}
//
//	@Override
//	public String getUsername() {
//		
//		return this.username;
//	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	
}
