// Entity class for all users.  Sets up 'users' table columns
package com.Team3.MaitreD.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.IncrementGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class ApplicationUser implements UserDetails{
	
	private static final long serialVersionUID = 1L; //I dont know what this does but Spring wanted it here
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "seq")
	@GenericGenerator(name = "seq", type=IncrementGenerator.class)
	private Integer user_id;
	@Column(unique=true)
	private String username;
	private String password;
	@Column(unique=true)
	private String email;
	private enum UserType {Customer, Restaurant}
	private UserType userType;
	@Lob
	private byte[] photo;
	private Integer account_id;
	
	// Creates join table for storing user roles
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
	        name="user_role_junction",
	        joinColumns = {@JoinColumn(name="user_id")},
	        inverseJoinColumns = {@JoinColumn(name="role_id")}
	    )
	private Set<Role> authorities;
	
	public ApplicationUser() {
		super();
		authorities = new HashSet<>();
	}
	

	public ApplicationUser(Integer user_id, String username, String email, String password, String userType, Set<Role> authorities, Integer account_id) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userType = UserType.valueOf(userType);
		this.authorities = authorities;
		this.account_id = account_id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}
	
	public boolean hasRole(String role) {
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		for (Role authority: this.authorities) {
			roles.add(new SimpleGrantedAuthority(authority.getAuthority()));
		}
		if (roles.toString().contains(role))
			return true;
		return false;
	}
	
	public byte[] getPhoto() {
		return photo;
	}


	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Integer getUserId() {
		return this.user_id;
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


	public UserType getUserType() {
		return userType;
	}


	public void setUserType(UserType userType) {
		this.userType = userType;
	}


	public Integer getAccount_id() {
		return account_id;
	}


	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

}
