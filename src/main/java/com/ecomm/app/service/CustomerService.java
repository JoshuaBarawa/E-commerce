package com.ecomm.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecomm.app.doa.CustomerRepository;
import com.ecomm.app.model.Customer;
import com.ecomm.app.model.CustomerRole;




@Service
public class CustomerService implements UserDetailsService{
	
   @Autowired
	private CustomerRepository userRepository;

	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 return userRepository.findByEmail(email);
	}

	public String signUpUser(Customer customer) {
		

		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		customer.setAppUserRole(CustomerRole.USER);

			 userRepository.save(customer);
		 
		return "Saved Successful";
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
