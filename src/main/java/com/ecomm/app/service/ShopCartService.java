package com.ecomm.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.app.doa.CartItemRepository;
import com.ecomm.app.model.Cart_Item;
import com.ecomm.app.model.Customer;


@Service
public class ShopCartService {

	@Autowired
	private CartItemRepository cartRepository;
	
	
	public List<Cart_Item> getItems(Customer customer){
		return (List<Cart_Item>) cartRepository.findAllByCustomer(customer);
	}

	public Cart_Item getItem(Long id) {
		return cartRepository.findById(id).get();
	}
	
	public void addToCart(Cart_Item cartItem) {
		cartRepository.save(cartItem);
	}
	
	public void removeItem(Long id) {
		cartRepository.deleteById(id);
	}
	

	public void removeAll() {
		cartRepository.deleteAll();
	}
}
