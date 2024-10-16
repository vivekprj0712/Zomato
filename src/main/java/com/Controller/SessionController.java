package com.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.CustomerEntity;
import com.Entity.RestaurantEntity;
import com.Entity.RoleEntity;
import com.Repository.CustomerAddressRepository;
import com.Repository.CustomerRepository;
import com.Repository.RestaurantRepository;
import com.Repository.RoleRepository;
import com.Service.MailerService;



@RestController
@RequestMapping("/api/public/session")
public class SessionController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	CustomerAddressRepository customerAddressRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	MailerService mailerService;
	
	// Signup & add customer
	@PostMapping("customersignup")
	public ResponseEntity<String>  customerSignup(@RequestBody CustomerEntity customerEntity) {

		Optional<RoleEntity> role = roleRepository.findById(2);
		CustomerEntity customerEmail = customerRepository.findByEmail(customerEntity.getEmail());
		//Email Present?
		if(customerEmail == null) {
			return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
		}if(role.isEmpty()) {
			return new ResponseEntity<>("Customer has no roles assigned", HttpStatus.FORBIDDEN);
		}
		else {
			customerEntity.setRole(role.get());
			customerRepository.save(customerEntity);
			return new ResponseEntity<>("Customer registered Successfully", HttpStatus.CREATED);
		
		}
	}
	
	//Signup & add Restaurant
		@PostMapping("restaurants")
		public ResponseEntity<String> addRestaurant(@RequestBody RestaurantEntity restaurantEntity) {
			Optional<RoleEntity> role = roleRepository.findById(3);
			if(role.isEmpty()) {
				return new ResponseEntity<>("Restaurant has no roles assigned", HttpStatus.FORBIDDEN);
			}else {
				restaurantEntity.setRole(role.get());
				restaurantRepository.save(restaurantEntity);
				return new ResponseEntity<>("Restaurant registered Successfully", HttpStatus.CREATED);
			}
		}
	
	@GetMapping("authentication/{email}/{password}")   // Pending...
	public ResponseEntity<String> authentication(@PathVariable("email") String email , @PathVariable("password") String password) {
		
		   CustomerEntity loggedInCustomer = customerRepository.findByEmail(email);
		   RestaurantEntity loggedInRestaurant = restaurantRepository.findByEmail(email);

		if(loggedInCustomer == null && loggedInRestaurant == null) {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}else {
			
			if(loggedInCustomer != null) {
				
				if(!loggedInCustomer.getPassword().matches(password)) {
					return new ResponseEntity<>("Password not matches", HttpStatus.UNAUTHORIZED);
				}else if(loggedInCustomer.getRole() == null) {
					return new ResponseEntity<>("Customer has no roles assigned", HttpStatus.FORBIDDEN);
				}else if(loggedInCustomer.getRole().getRoleId() == 2) {
					System.out.println(loggedInCustomer.getRole());
					System.out.println();
					return new ResponseEntity<>("Login SuccessFul", HttpStatus.OK);
				}
			}else if(loggedInRestaurant != null) {
					
					if(!loggedInRestaurant.getPassword().matches(password)) {
						return new ResponseEntity<>("Password not matches", HttpStatus.UNAUTHORIZED);
					}else if(loggedInRestaurant.getRole() == null) {
						return new ResponseEntity<>("Restaurant has no roles assigned", HttpStatus.FORBIDDEN);
					}else if(loggedInRestaurant.getRole().getRoleId() == 3) {
						System.out.println(loggedInRestaurant.getRole());
						return new ResponseEntity<>("Login Is SuccessFul", HttpStatus.OK);
					}
				}
			}
		return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}

	@GetMapping("sendotpforrecoverpassword/{email}")
	public ResponseEntity<?> sendOtpForRecoverPassword(@PathVariable("email") String email , CustomerEntity customerEntity) {
		CustomerEntity dbCustomer = customerRepository.findByEmail(email);
		if(dbCustomer != null) {
			int otp = (int)(Math.random()*1000000);
			 mailerService.sendMailForOtp(email, otp);
			 dbCustomer.setOtp(otp);
			 customerRepository.save(dbCustomer);
			 return new ResponseEntity<>("Opt Send", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("updatepassword/{email}/{password}/{confirmpassword}/{otp}") 
	public ResponseEntity<String> updatePassword(@PathVariable("email") String email , @PathVariable("password") String password,@PathVariable("confirmpassword") String confirmpassword,@PathVariable("otp") Integer otp) {
		CustomerEntity dbCustomer = customerRepository.findByEmail(email);
		if(!password.equals(confirmpassword)) {
			System.out.println("error...1");
			return new ResponseEntity<>("Password and Confirm Password do not match", HttpStatus.BAD_REQUEST);
		}else {
			if (dbCustomer == null) {
		        System.out.println("error...2");
		        return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
		    }
			if( otp == -1 || otp == null)
			{
				return new ResponseEntity<>("OTP not provided", HttpStatus.BAD_REQUEST);
			}else if(dbCustomer.getOtp().intValue() != otp || dbCustomer.getOtp() == null)
				{
				return new ResponseEntity<>("OTP does not match", HttpStatus.UNAUTHORIZED);
				}
			else {
				dbCustomer.setPassword(password);
				dbCustomer.setOtp(-1);
				customerRepository.save(dbCustomer);
				System.out.println("Password updated successfully for customer");
				return new ResponseEntity<>("Password updated Successfully", HttpStatus.OK);
			}
		}
	}
}
