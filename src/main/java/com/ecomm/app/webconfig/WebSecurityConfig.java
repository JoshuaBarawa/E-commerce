package com.ecomm.app.webconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.ecomm.app.service.CustomerService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomerService customerService;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customerService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/user/**")
		.permitAll().antMatchers("/shopme/home").permitAll()
		.antMatchers("/shopme/displayimage/{id}").permitAll()
		.antMatchers("/shopme/products/{id}").permitAll()
		.antMatchers("/shopme/categories").permitAll()
		.antMatchers("/shopme/display/{id}").permitAll()
		.antMatchers("/shopme/viewitem/{id}").permitAll()
		.antMatchers("/shopme/adminpage").hasAuthority("ADMIN")
		.antMatchers("/css/*","/js/*","/img/*").permitAll()
	    .anyRequest()
	    .authenticated().and()
	    .formLogin().loginPage("/login")
	    .defaultSuccessUrl("/shopme/home").permitAll()
	    .and().logout().logoutUrl("/logout").permitAll();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}