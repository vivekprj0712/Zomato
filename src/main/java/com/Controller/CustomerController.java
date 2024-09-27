package com.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.CustomerEntity;
import com.Repository.CustomerRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class CustomerController {

	
	@Autowired
	CustomerRepository customerRepository;
	
	
	// read All Customer 
	@GetMapping("customers")
	public List<CustomerEntity> getAllCustomers() {
		List<CustomerEntity> customers = customerRepository.findAll();
		return customers;
	}
	
	// read customer by Id
	@GetMapping("customers/{customerId}")
	public CustomerEntity getCustomerById(@PathVariable("customerId") Integer customerId ) {
		Optional<CustomerEntity> customers = customerRepository.findById(customerId);
		if(customers.isEmpty()) {
			return null;
		}else {
			CustomerEntity customerEntity = customers.get();
			return customerEntity;
		}
		
	}
	//delete customer by id
	@DeleteMapping("customers/{customerId}")
	public CustomerEntity deleteCustomerById(@PathVariable("customerId") Integer customerId , CustomerEntity customerEntity)
	{
		Optional<CustomerEntity> customer = customerRepository.findById(customerId);
		if(customer.isEmpty()) {
			return null;
		}else {
			customerRepository.deleteById(customerId);
			return customerEntity;
		}
	}
	 @PutMapping("customers/{customerId}")
	 public CustomerEntity updateCustomerProfile(@PathVariable ("customerId") Integer customerId, @RequestBody CustomerEntity customerEntity) {
	 	customerRepository.save(customerEntity);
	 	return customerEntity;
	 }
}
