package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.CustomerAddressEntity;
import com.Repository.CustomerAddressRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CustomerController {

	@Autowired
	CustomerAddressRepository customerAddressRepository;
	
	@PostMapping("customeraddress")
	public CustomerAddressEntity customerAddress(@RequestBody CustomerAddressEntity customerAddressEntity) {
		
		customerAddressRepository.save(customerAddressEntity);
		return customerAddressEntity;
	}
	
}
