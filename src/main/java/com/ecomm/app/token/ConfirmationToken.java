package com.ecomm.app.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ecomm.app.model.Customer;

@Table(name = "confirmationtoken")
@Entity
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "token", nullable = false)
	private String token;

	@Column(name = "createdat", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "expiresat", nullable = false)
	private LocalDateTime expiresAt;

	@Column(name = "confirmedat")
	private LocalDateTime confirmedAt;

	@ManyToOne
	@JoinColumn(name = "users_usercode", nullable = false)
	private Customer appUser;

	public ConfirmationToken() {

	}

	public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, Customer appUser) {
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.appUser = appUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public LocalDateTime getConfirmedAt() {
		return confirmedAt;
	}

	public void setConfrimedAt(LocalDateTime confirmedAt) {
		this.confirmedAt = confirmedAt;
	}

	public Customer getAppUser() {
		return appUser;
	}

	public void setAppUser(Customer appUser) {
		this.appUser = appUser;
	}

}
