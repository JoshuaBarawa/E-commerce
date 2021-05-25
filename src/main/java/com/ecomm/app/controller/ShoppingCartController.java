package com.ecomm.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ecomm.app.model.Cart_Item;
import com.ecomm.app.model.Customer;
import com.ecomm.app.model.Product;
import com.ecomm.app.service.CustomerService;
import com.ecomm.app.service.ProductService;
import com.ecomm.app.service.ShopCartService;

@Controller
@RequestMapping(path="/shopme")
public class ShoppingCartController {
	
	@Autowired
	private ShopCartService shopCartService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/cart")
	public ModelAndView getCartItems(ModelAndView view, @AuthenticationPrincipal Authentication auth) {
		Customer customer = customerService.getCurrentLoogedInCustomer(auth);
		List<Cart_Item> cartItems = shopCartService.getItems(customer);
		
		int total = 0;
		Long id = null;
		for(Cart_Item item: cartItems) {
			
			total = item.getQuantity() *item.getProduct().getPrice() + total;
			id = item.getProduct().getCategoryid().getId();
		}
		view.getModel().put("cartitems", cartItems);
	    view.getModel().put("total", total);
	    view.getModel().put("id", id);
		view.setViewName("app.shoppingcart");
		return view;
	}
	
	@PostMapping("/addtocart/{id}")
	public ModelAndView addToCart(ModelAndView view,@PathVariable("id")Long id,@RequestParam("quantity")int quantity,
			@AuthenticationPrincipal Authentication auth) {
		
		Customer customer = customerService.getCurrentLoogedInCustomer(auth);
		
		Product product = productService.getProduct(id);
		Cart_Item item = new Cart_Item();
		item.setId(product.getId());
		item.setProduct(product);
		item.setCustomer(customer);
		item.setQuantity(quantity);
		
		shopCartService.addToCart(item);
		getItem(view, id);
	    view.getModel().put("msg", quantity + " " + product.getName() +" Added To Cart");
		
		return view;
	}
	
	@GetMapping("/viewitem/{id}")
	public ModelAndView getItem(ModelAndView view,@PathVariable("id")Long id) {
		view.setViewName("app.itempage");
		
		List<Product> product =  productService.getProd(id);
		view.getModel().put("items", product);
	    return view;
	}
	

	@GetMapping("/remove/{id}")
	public ModelAndView remove(ModelAndView view,@PathVariable("id") Long id,@AuthenticationPrincipal Authentication auth ) {
		view.setViewName("app.shoppingcart");
		
		shopCartService.removeItem(id);
		getCartItems(view, auth);
		return view;
	}
}
