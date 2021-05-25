package com.ecomm.app.doa;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecomm.app.model.Customer;

@Repository
@Transactional(readOnly = true)
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
 Customer findByEmail(String email);
 
 Customer findByUserName(String userName);


}
