package com.ecomm.app.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ecomm.app.model.Cart_Item;
import com.ecomm.app.model.Customer;
import com.ecomm.app.model.Order;
import com.ecomm.app.service.CustomerService;
import com.ecomm.app.service.PaypalService;
import com.ecomm.app.service.ProductService;
import com.ecomm.app.service.ShopCartService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
public class PaypalController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ShopCartService shopCartService;

	@Autowired
	PaypalService service;

	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";

	@GetMapping("/checkout")
	public ModelAndView home(ModelAndView view, @AuthenticationPrincipal Authentication auth) {
		
		view.setViewName("app.checkout");
		Customer customer = customerService.getCurrentLoogedInCustomer(auth);
		List<Cart_Item> cartItems = shopCartService.getItems(customer);
		
		int total = 0;
		Long id = null;
		for(Cart_Item item: cartItems) {
			
			total = item.getQuantity() *item.getProduct().getPrice() + total;
			id = item.getProduct().getCategoryid().getId();
			
		}
		
		 view.getModel().put("total", total);
		
		
		return view;
	}	
	
	@PostMapping("/pay")
	public String payment( @ModelAttribute("order") Order order) {
		try {
			
			Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
					order.getIntent(), order.getDescription(), "http://localhost:8080/" + CANCEL_URL,
					"http://localhost:8080/" + SUCCESS_URL);
			for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					return "redirect:"+link.getHref();
				}
			}
			
		} catch (PayPalRESTException e) {
		
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	
	@GetMapping(value = CANCEL_URL)
    public ModelAndView cancelPay(ModelAndView view) {
     view.setViewName("app.cancel");
		
		return view;
    }

    @GetMapping(value = SUCCESS_URL)
    public ModelAndView successPay(ModelAndView view, @RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,
    		@AuthenticationPrincipal Authentication auth) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
            	view.setViewName("app.success");
            	Customer customer = customerService.getCurrentLoogedInCustomer(auth);
            	productService.deleteAllProducts(customer.getId());
            	
                return view;
            }
        } catch (PayPalRESTException e) {
         System.out.println(e.getMessage());
        }
        return view;
    }

	
}
