package com.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.CustomerAddressEntity;
import com.Repository.CustomerAddressRepository;
import com.Repository.CustomerRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/private/customeraddress")
public class CustomerAddressController {

	@Autowired
	CustomerAddressRepository customerAddressRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	// add customerAddrees
	@PostMapping("customeraddress")
	public ResponseEntity<String> customerAddress(@RequestBody CustomerAddressEntity customerAddressEntity) {
		customerAddressRepository.save(customerAddressEntity);
		return new ResponseEntity<>("Record Added Successfully",HttpStatus.CREATED);
	}
	
	//read All customerAddress
		@GetMapping("customeraddress")
		public ResponseEntity<?> getAllCustomerAddress() {
			List<CustomerAddressEntity> customerAddress = customerAddressRepository.findAll();
			if(customerAddress.isEmpty()) {
				return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
			}else {
			    return new ResponseEntity<>(customerAddress , HttpStatus.OK);
			}
		}
		
		
	//read customerAddress by customer Id
	@GetMapping("customeraddress/{customerId}")
	public ResponseEntity<?> getCustomerAddressById(@PathVariable("customerId") Integer customerId) {
		Optional<CustomerAddressEntity> customerAddress = customerAddressRepository.findById(customerId);
		if(customerAddress.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
		}else {
			CustomerAddressEntity customerAddressEntity = customerAddress.get();
			return new ResponseEntity<>(customerAddressEntity , HttpStatus.OK);
		}
	}
	
	@DeleteMapping("customeraddress/{customeraddressId}")
	public ResponseEntity<String> deleteCustomerAddress(@PathVariable("customeraddressId") Integer customeraddressId) {
		Optional<CustomerAddressEntity> customerAddress = customerAddressRepository.findById(customeraddressId);
		if(customerAddress.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>("Record Deleted Successfully" , HttpStatus.OK);
		}
	}
	
	// update customerAddress by Id
	@PutMapping("customeraddress/{customeraddressId}")
	public ResponseEntity<?> updateCustomerAddress(@PathVariable("customeraddressId") Integer customeraddressId, @RequestBody CustomerAddressEntity customerAddressEntity) {
			
			customerAddressRepository.save(customerAddressEntity);
			return new ResponseEntity<>("Record Updated Successfully",HttpStatus.OK);
		
	}
	
}
