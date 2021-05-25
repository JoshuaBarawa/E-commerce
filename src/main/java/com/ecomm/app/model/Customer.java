package com.ecomm.app.model;


import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "customers" )
public class Customer implements UserDetails{


	private static final long serialVersionUID = 2558066865027328988L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",unique = true, nullable = false)
	private Long id;
	
	@Column(name = "username" ,nullable = false,unique = true)
	private String userName;
	
	@Column(name = "email",nullable = false,unique = true)
	private String email;
	
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role",nullable = false)
	private CustomerRole appUserRole;
	
	@Column(name = "locked",nullable = false)
	private boolean locked =false;
	
	@Column(name = "enabled",nullable = false)
	private boolean enabled =true;
	
	
	

	public Customer() {

	}

	public Customer(String userName, String email, String password, CustomerRole appUserRole
			) {
	
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
		return Collections.singleton(authority);
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CustomerRole getAppUserRole() {
		return appUserRole;
	}

	public void setAppUserRole(CustomerRole appUserRole) {
		this.appUserRole = appUserRole;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public String getUserName() {
		return userName;
	}
	
	
}
