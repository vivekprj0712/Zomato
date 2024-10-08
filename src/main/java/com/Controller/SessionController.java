package com.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.CustomerEntity;
import com.Entity.RestaurantEntity;
import com.Entity.RoleEntity;
import com.Repository.CustomerRepository;
import com.Repository.RestaurantRepository;
import com.Repository.RoleRepository;
import com.Service.MailerService;



@RestController
public class SessionController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	MailerService mailerService;
	
	// Signup & add customer
	@PostMapping("customersignup")
	public CustomerEntity customerSignup(@RequestBody CustomerEntity customerEntity) {
		Optional<RoleEntity> role = roleRepository.findById(2);
		customerEntity.setRole(role.get());
		customerRepository.save(customerEntity);
		return customerEntity;
	}
	
	@GetMapping("authentication/{email}/{password}") 
	public String authentication(@PathVariable("email") String email , @PathVariable("password") String password) {
		CustomerEntity loggedInCustomer = customerRepository.findByEmail(email);
		RestaurantEntity loggedInRestaurant = restaurantRepository.findByEmail(email);
		if(loggedInCustomer == null && loggedInRestaurant == null) {
			return "not Found";
		}else {
			
			if(loggedInCustomer != null) {
				
				if(!loggedInCustomer.getPassword().matches(password)) {
					return "password Invalid";
				}else if(loggedInCustomer.getRole() == null) {
					return "Invalid Role";
				}else if(loggedInCustomer.getRole().getRoleName().equalsIgnoreCase("CUSTOMER") ) {
					System.out.println(loggedInCustomer.getRole());
					System.out.println();
					return "Customer";	
				}
			}else if(loggedInRestaurant != null) {
					
					if(!loggedInRestaurant.getPassword().matches(password)) {
						return "password Invalid";
					}else if(loggedInRestaurant.getRole() == null) {
						return "Invalid Role";
					}else if(loggedInRestaurant.getRole().getRoleId() == 3) {
						System.out.println(loggedInRestaurant.getRole());
						return "Restaurant";
					}
				}
			}
		return "Login"; //Login
		}

	@GetMapping("sendotpforrecoverpassword/{email}")
	public CustomerEntity sendOtpForRecoverPassword(@PathVariable("email") String email , CustomerEntity customerEntity) {
		CustomerEntity dbCustomer = customerRepository.findByEmail(email);
		if(dbCustomer != null) {
			int otp = (int)(Math.random()*1000000);
			 mailerService.sendMailForOtp(email, otp);
			 dbCustomer.setOtp(otp);
			 customerRepository.save(dbCustomer);
			 return dbCustomer;
		}else {
			return null;
		}
	}
	
	@GetMapping("updatepassword/{email}/{password}/{confirmpassword}/{otp}") 
	public CustomerEntity updatePassword(@PathVariable("email") String email , @PathVariable("password") String password,@PathVariable("confirmpassword") String confirmpassword,@PathVariable("otp") Integer otp) {
		if(!password.equals(confirmpassword)) {
			System.out.println("error...1");
			return null;
		}else {
			CustomerEntity dbCustomer = customerRepository.findByEmail(email);
			
			if (dbCustomer == null) {
		        System.out.println("error...2");
		        return null;
		    }
			if( otp == -1 || dbCustomer.getOtp().intValue() != otp || dbCustomer.getOtp() == null)
			{
				System.out.println("error...333");
				System.out.println(otp);
				System.out.println(dbCustomer.getOtp());
				System.out.println("===");
				System.out.println(otp==-1);
				System.out.println(	dbCustomer.getOtp() != otp);
				System.out.println(dbCustomer.getOtp() == null);

						 return null;
			}else {
				dbCustomer.setPassword(password);
				dbCustomer.setOtp(-1);
				customerRepository.save(dbCustomer);
				System.out.println("Password updated successfully for customer");
				return dbCustomer;
			}
		}
	}
}
