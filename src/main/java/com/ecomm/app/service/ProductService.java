package com.ecomm.app.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecomm.app.doa.ProductRepository;
import com.ecomm.app.model.Category;
import com.ecomm.app.model.Product;

@Service
public class ProductService {
	
	@Autowired
    private ProductRepository productRepository;

	public String addProduct(MultipartFile file, String name,String description, int price,String brand,Category categoryid) throws IOException{
		Product product = new Product()	;
		product.setPhoto(Base64.getEncoder().encode(file.getBytes()));
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setBrand(brand);
		product.setCategoryid(categoryid);
	     productRepository.save(product);
		return "Added Successfully";
	}
	
	public List<Product> getProducts(){
		return (List<Product>) productRepository.findAll();
	}
	
	public List<Product> getProductsByCategoryId(Category id){
		return (List<Product>) productRepository.findAllByCategoryid(id);
	}
	
	public List<Product> getProd(Long id){
		return  productRepository.findAllById(id);
	}
	
	public Product getProduct(Long id){
		return productRepository.findById(id).get();
	}

	public void editProduct(Long id, Product product) throws IOException {
	
		 Product prod = getProduct(id);
		 
		 prod.setPhoto(prod.getPhoto());
		 prod.setName(product.getName());
		 prod.setCategoryid(prod.getCategoryid());
		 prod.setDescription(product.getDescription());
		 prod.setPrice(product.getPrice());
		 prod.setBrand(product.getBrand());
		
		productRepository.save(prod);
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	public void deleteAllProducts(Long id) {
		productRepository.deleteAllById(id);
	}
}
