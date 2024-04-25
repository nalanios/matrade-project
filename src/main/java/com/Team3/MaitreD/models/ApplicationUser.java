// Entity class for all users.  Sets up 'users' table columns
package com.Team3.MaitreD.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.IncrementGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="users")
public class ApplicationUser implements UserDetails{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "seq")
	@GenericGenerator(name = "seq", type=IncrementGenerator.class)
	private Integer userID;
	@Column(unique=true)
	private String username;
	private String password;
	@Column(unique=true)
	private String email;
	private Integer customerOrRestaurantID;
	
	// Creates join table for storing user roles
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
	        name="user_role_junction",
	        joinColumns = {@JoinColumn(name="user_id")},
	        inverseJoinColumns = {@JoinColumn(name="role_id")}
	    )
	private Set<Role> authorities = new HashSet<>();
	
	public ApplicationUser() {
		super();
	}
	

	public ApplicationUser(Integer user_id, String username, String email, String password,  Set<Role> authorities, Integer customerOrRestaurantID) {
		super();
		this.userID = user_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
		this.customerOrRestaurantID = customerOrRestaurantID;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}

	public Integer getUserId() {
		return this.userID;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Integer getcustomerOrRestaurantID() {
		return customerOrRestaurantID;
	}


	public void setcustomerOrRestaurantID(Integer accountID) {
		this.customerOrRestaurantID = accountID;
	}

}
