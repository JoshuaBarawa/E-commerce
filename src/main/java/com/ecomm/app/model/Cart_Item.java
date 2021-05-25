package com.ecomm.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cartitems")
public class Cart_Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name ="productid")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name ="customerid")
	private Customer customer;
	
	@Column(name ="quantity")
	private int quantity;
	
	public Cart_Item() {
		
	}

	public Cart_Item(Product product, Customer customer,int quantity) {
		this.product = product;
		this.customer=customer;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Cart_Item [id=" + id + ", customerid="  + customer.getId() + customer.getEmail() + ", product=" +product.getId()+ product.getName() + product.getPrice()+  ", quantity=" + quantity + "]";
	}
	
}
