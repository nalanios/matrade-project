
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
public class ApplicationUser implements UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "seq")
	@GenericGenerator(name = "seq", strategy=IncrementGenerator.class)
	private Integer userID;
	@Column(unique=true)
	private String username;
	private String password;
	@Column(unique=true)
	private String email;
	private Integer customerOrRestaurantID;
	@Column
	private String imagePath; // Path to the image file

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

	public ApplicationUser(Integer user_id, String username, String email, String password, Set<Role> authorities, Integer customerOrRestaurantID, String imagePath) {
		super();
		this.userID = user_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
		this.customerOrRestaurantID = customerOrRestaurantID;
		this.imagePath = imagePath; // Initialize image path
	}

	// Getters and setters
	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}

	public Integer getCustomerOrRestaurantID() {
		return customerOrRestaurantID;
	}

	public void setCustomerOrRestaurantID(Integer customerOrRestaurantID) {
		this.customerOrRestaurantID = customerOrRestaurantID;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
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
}
