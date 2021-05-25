package com.ecomm.app.controller;
import java.io.IOException;

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
import com.ecomm.app.model.Product;
import com.ecomm.app.service.CategoryService;
import com.ecomm.app.service.ProductService;


@Controller
public class ProductController {
	

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/shopme/adminpage")
	public ModelAndView adminpage(ModelAndView view) {
		view.setViewName("app.adminpage");
		
		return view;
	}
	
	
	@GetMapping("/shopme/addproduct")
	public ModelAndView addProductForm(ModelAndView modelAndView) {
		modelAndView.setViewName("app.addproduct");
		
		Product product = new Product();
		
		modelAndView.getModel().put("product", product);
		modelAndView.getModel().put("categories", categoryService.getCategories());
		
		return modelAndView;
	}
	
	@PostMapping("/shopme/addedproduct")
	public ModelAndView addedProduct(ModelAndView modelAndView,@RequestParam("photo")MultipartFile file,
			@RequestParam("name")String name,@RequestParam("description")String description,@RequestParam("price")int price,@RequestParam("brand")String brand,
			@RequestParam("categoryid")Category categoryid)  {
		modelAndView.setViewName("app.adminpage");
		try {
			productService.addProduct(file,name,description,price,brand,categoryid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return modelAndView;
	}
	
	@GetMapping("/shopme/display/{id}")
	public void displayImage(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
		Product product = productService.getProduct(id);
		byte[] image = Base64Utils.decode(product.getPhoto());
		
		response.setContentType("image/jpeg,image/jpg,image/gif");
		response.getOutputStream().write(image);
	
	}
	
	@GetMapping("/shopme/products/{id}")
	public ModelAndView getProducts(ModelAndView view,@PathVariable("id")Category id) {
		view.setViewName("app.products");
	
		view.getModel().put("products", productService.getProductsByCategoryId(id));
		return view;
	}
	
}
