package com.ecomm.app.doa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.app.model.Cart_Item;
import com.ecomm.app.model.Customer;

@Repository
public interface CartItemRepository extends CrudRepository<Cart_Item, Long>{

	List<Cart_Item> findAllByCustomer(Customer customer);
	
	
}
