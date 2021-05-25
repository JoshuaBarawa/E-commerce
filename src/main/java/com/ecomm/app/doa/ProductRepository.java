package com.ecomm.app.doa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.app.model.Category;
import com.ecomm.app.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

List<Product> findAllByCategoryid(Category id);

List<Product> findAllById(Long id);

  void deleteAllById(Long id);
	 
}
