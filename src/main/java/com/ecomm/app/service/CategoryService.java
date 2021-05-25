package com.ecomm.app.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.ecomm.app.doa.CategoryRepository;
import com.ecomm.app.model.Category;



@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRep;
	
	
	public String addCategory(MultipartFile file, String name ) throws IOException{

		Category category = new Category();
		category.setName(name);
		category.setPhoto(Base64.getEncoder().encode(file.getBytes()));
		categoryRep.save(category);
		
		return "Added Successfully";
	}
	
	public List<Category> getCategories(){
		return (List<Category>) categoryRep.findAll();
	}

	public Category getCategoy(Long id){
		return categoryRep.findById(id).get();
	}

	public void editProduct(Long id, Category category) {
		Category cate = getCategoy(id);
		 cate.setName(category.getName());
		 
		
		 categoryRep.save(category);
	}
	
	public void deleteProduct(Long id) {
		categoryRep.deleteById(id);
	}


}
