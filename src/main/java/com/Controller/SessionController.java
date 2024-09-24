package com.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.Entity.CustomerEntity;
import com.Repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class SessionController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@PostMapping("customersignup")
	public CustomerEntity customerSignup(@RequestBody CustomerEntity customerEntity) {
		customerRepository.save(customerEntity);
		return customerEntity;
	}
	

}
