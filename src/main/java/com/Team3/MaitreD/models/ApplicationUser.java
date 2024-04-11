package com.Team3.MaitreD.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

@Entity
@Table(name="users")
public class ApplicationUser implements UserDetails{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer user_id;
	@Column(unique=true)
	private String username;
	private String password;
	@Column(unique=true)
	private String email;
	enum user_type {customer, owner, staff}
	
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
	

	public ApplicationUser(Integer user_id, String username, String email, String password, Set<Role> authorities) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
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

}
