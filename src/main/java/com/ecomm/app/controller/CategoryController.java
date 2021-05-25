package com.ecomm.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.ecomm.app.model.Category;
import com.ecomm.app.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/shopme/home")
	public ModelAndView getCategories(ModelAndView view) {
		
		view.setViewName("app.home");
		 List<Category> categories = categoryService.getCategories();
		  view.getModel().put("categories", categories);
		return view;
	}
	
	
	@GetMapping("/shopme/addcategory")
	public ModelAndView addCategoryForm(ModelAndView modelAndView) {
		modelAndView.setViewName("app.addcategory");
		
		Category category = new Category();
		
		modelAndView.getModel().put("category", category);
		
		return modelAndView;
	}
	
	@PostMapping("/shopme/addedcategory")
	public ModelAndView addedCategory(ModelAndView modelAndView,@RequestParam("photo") MultipartFile file,
			@RequestParam("name") String name) throws IOException  {
			
		modelAndView.setViewName("app.adminpage");
		categoryService.addCategory(file ,name);
		return modelAndView;
	}
	
	@GetMapping("/shopme/displayimage/{id}")
	public void displayImage(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
		Category category = categoryService.getCategoy(id);
		byte[] image = Base64Utils.decode(category.getPhoto());
		
		response.setContentType("image/jpeg,image/jpg,image/gif");
		response.getOutputStream().write(image);
	
	}
	
	
}
