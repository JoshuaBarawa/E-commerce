package com.ecomm.app.customer.controller;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       

import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ecomm.app.email.EmailSender;
import com.ecomm.app.model.Customer;
import com.ecomm.app.service.CustomerService;



@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailSender emailSender;
	
	@GetMapping("/user/signup")
	public ModelAndView registration(ModelAndView view) {
		 view.setViewName("app.signup");
		 view.getModel().put("customer", new Customer());

		 return view;
	}
	
	
	@PostMapping("/user/signedup")
	public ModelAndView registration(ModelAndView view, @ModelAttribute("customer") @Valid Customer customer,BindingResult result) {
	
		Customer custom = (Customer) customerService.loadUserByUsername(customer.getEmail());

		if(custom != null) {
			view.setViewName("app.signup");
			view.getModel().put("error", "Email Address Already Exists!!");
		}
		
		else {
		customerService.signUpUser(customer);
		view.setViewName("app.login");
		view.getModel().put("msg", "Registraion was Successful!!");
		}

		 return view;
	}
	
	@RequestMapping("/login")
	public ModelAndView loginpage(ModelAndView view) {
		view.setViewName("app.login");

		 return view;
	}
	
	
	@RequestMapping("/shopme/account")
	public ModelAndView customerpage(ModelAndView view, @AuthenticationPrincipal Authentication auth) {
		view.setViewName("app.profile");
		Customer customer = customerService.getCurrentLoogedInCustomer(auth);
		view.getModel().put("customer", customer);
		
		 return view;
	}
	
	
	@PostMapping("/shopme/updateaccount")
	public ModelAndView updateCustomer(ModelAndView view,@AuthenticationPrincipal Authentication auth,Customer customer ) {
		view.setViewName("app.profile");
		customerService.updateCustomer(auth, customer);
		 return view;
	}
	
	
	
	@GetMapping("/user/confirm/{token}")
	public ModelAndView confirmToken(ModelAndView view,@PathVariable String token) {
		view.setViewName("app.login");
		
		customerService.confirmToken(token);
		return view;
	}
	
	
	@GetMapping("/user/resetpassword")
	public ModelAndView passReset(ModelAndView view) {
		view.setViewName("app.resetpassword");
		
		return view;
	}
	
	@PostMapping("/user/sendemail")
	public ModelAndView passwordReset(ModelAndView view,@RequestParam("email")String email) {
		view.setViewName("app.resetpassword");
		Customer user = (Customer) customerService.loadUserByUsername(email)  ;
		if(user!= null) {
			
			Random code = new Random();
			int password = code.nextInt(99999);
			
			user.setPassword(passwordEncoder.encode(String.valueOf(password)));
			
			customerService.editCustomer(user);
			emailSender.sendEmail(email, String.valueOf(password));
			
			view.getModel().put("msg", " A reset code has be sent to your email."+ 
					" Use it to login and reset your password");
			view.setViewName("app.resetpassword");
		}
		
		else {
			view.getModel().put("error", "Email does not exist!!");
			view.setViewName("app.resetpassword");		
		}
		
		return view;
	}
	
	
	

}
