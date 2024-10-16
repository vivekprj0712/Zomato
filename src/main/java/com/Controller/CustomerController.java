package com.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.CustomerEntity;
import com.Repository.CustomerRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/private/customer")
public class CustomerController {

	
	@Autowired
	CustomerRepository customerRepository;
	
	
	// read All Customer 
	@GetMapping("customers")
	public ResponseEntity<?> getAllCustomers() {
		List<CustomerEntity> customers = customerRepository.findAll();
		if(customers.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(customers,HttpStatus.OK);
		}
	}
	
	// read customer by Id
	@GetMapping("customers/{customerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable("customerId") Integer customerId ) {
		Optional<CustomerEntity> customers = customerRepository.findById(customerId);
		if(customers.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);		
		}else {
			CustomerEntity customerEntity = customers.get();
			return new ResponseEntity<>(customerEntity,HttpStatus.OK);
		}
		
	}
	//delete customer by id
	@DeleteMapping("customers/{customerId}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable("customerId") Integer customerId , CustomerEntity customerEntity)
	{
		Optional<CustomerEntity> customer = customerRepository.findById(customerId);
		if(customer.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);	
		}else {
			customerRepository.deleteById(customerId);
			return new ResponseEntity<>("Record Deleted Successfully",HttpStatus.OK);
		}
	}
	 @PutMapping("customers/{customerId}")
	 public ResponseEntity<String> updateCustomerProfile(@PathVariable ("customerId") Integer customerId, @RequestBody CustomerEntity customerEntity) {
	 	customerRepository.save(customerEntity);
	 	return new ResponseEntity<>("Record Updated Successfully",HttpStatus.OK);
	 }
}
