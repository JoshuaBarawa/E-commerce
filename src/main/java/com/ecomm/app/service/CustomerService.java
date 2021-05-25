package com.ecomm.app.service;


import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomm.app.doa.CustomerRepository;
import com.ecomm.app.email.EmailSender;
import com.ecomm.app.model.Customer;
import com.ecomm.app.model.CustomerRole;
import com.ecomm.app.token.ConfirmationToken;
import com.ecomm.app.token.ConfirmationTokenService;




@Service
public class CustomerService implements UserDetailsService{
	
   @Autowired
	private CustomerRepository userRepository;

	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private ConfirmationTokenService tokenService;
	

	@Autowired
	private EmailSender emailSender;
	
    
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 return userRepository.findByEmail(email);
	}

	public String signUpUser(Customer customer) {
		

		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		customer.setAppUserRole(CustomerRole.USER);

			 userRepository.save(customer);
		 
		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(
				token, 
				LocalDateTime.now(), 
				LocalDateTime.now().plusHours(5), 
				customer);
		
		tokenService.saveConfirmationToken(confirmationToken);
		
		String link = "http://localhost:8080/user/confirm/" + token;
		emailSender.sendEmail(customer.getEmail(), link);
	
		return token;
	}
	
	@Transactional
	public String confirmToken(String token) {
		
		ConfirmationToken confirmationToken = tokenService.getToken(token);
		
		if(confirmationToken.getConfirmedAt() != null) {
			return "Email already confirmed!!";
		}
		
		LocalDateTime expired = confirmationToken.getExpiresAt();
		
		if(expired.isBefore(LocalDateTime.now())) {
			return "Token already Expired!!";
		}
		
		tokenService.confirmedAt(token);
	   enableAppUser(confirmationToken.getAppUser().getEmail());
		
		return "Confirmed";
		
	}
	
	public boolean enableAppUser(String email) {
	Customer user = (Customer) loadUserByUsername(email);
	user.setEnabled(true);
		
    return true;
	}
	
	
	public boolean getCustomer(String email) {
		loadUserByUsername(email);
		 return true;
	}

	public Customer getCurrentLoogedInCustomer(Authentication auth) {
		if(auth ==null) return null;
		Customer customer=null;
		Object principal = auth.getPrincipal();
		
		if(principal instanceof UserDetails) {
			String name = ((UserDetails) principal).getUsername();
			customer = (Customer) loadUserByUsername(name);
		
		}
		
		return customer;
	}
	
	
	public void updateCustomer(Authentication auth, Customer customer) {
		Customer cust = getCurrentLoogedInCustomer(auth);
		 cust.setUserName(customer.getUserName());
		 cust.setEmail(customer.getEmail());
		 String password = passwordEncoder.encode(customer.getPassword());
		 cust.setPassword(password);
		
		 userRepository.save(cust);
	}
	
	public void editCustomer(Customer user) {
		userRepository.save(user);
	}
	
	
}
