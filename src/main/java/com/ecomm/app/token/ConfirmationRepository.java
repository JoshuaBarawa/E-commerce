package com.ecomm.app.token;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepository extends CrudRepository<ConfirmationToken, Long>{

	ConfirmationToken findByToken(String token);
	
	
}
