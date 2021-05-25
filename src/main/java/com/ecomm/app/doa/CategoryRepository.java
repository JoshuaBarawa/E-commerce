package com.ecomm.app.doa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.app.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{

	Category findByName(String name);	

}
