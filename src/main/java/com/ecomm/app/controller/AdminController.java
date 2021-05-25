package com.ecomm.app.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecomm.app.model.Category;
import com.ecomm.app.model.Product;
import com.ecomm.app.service.CategoryService;
import com.ecomm.app.service.ProductService;

@Controller
public class AdminController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/shopme/categories")
	public ModelAndView categories(ModelAndView view) {
		
		view.setViewName("app.removeproducts");
		
		 List<Category> categories = categoryService.getCategories();
		  view.getModel().put("categories", categories);
		return view;
	}
	
	@GetMapping("/shopme/adminproducts/{id}")
	public ModelAndView products(ModelAndView view,@PathVariable("id")Category id) {
		view.setViewName("app.adminproducts");
		view.getModel().put("products", productService.getProductsByCategoryId(id));
		return view;
	}
	
	@GetMapping("/shopme/updateproduct/{id}")
	public ModelAndView getProduct(ModelAndView view,@PathVariable("id")Long id) {
		view.setViewName("app.updateproduct");
		view.getModel().put("product", productService.getProduct(id));
		view.getModel().put("categories", categoryService.getCategories());
		
		return view;
	}
	
	@PostMapping("/shopme/editproduct/{id}")
	public ModelAndView updateProduct(ModelAndView view,@PathVariable("id")Long id,@Valid @ModelAttribute("product")Product product
			,BindingResult result) throws IOException {
		view.setViewName("app.adminproducts");
		productService.editProduct(id, product);
		categories(view);
	    view.getModel().put("mssg", "Product updated successful");
		return view;
	}
	
	
	@GetMapping("/shopme/removeproduct/{id}")
	public ModelAndView removeProduct(ModelAndView view,@PathVariable("id")Long id) {
		view.setViewName("app.removeproducts");

		productService.deleteProduct(id);
		categories(view);
	    view.getModel().put("mssg", "Product was deleted successful");
		return view;
	}
	
	
}
