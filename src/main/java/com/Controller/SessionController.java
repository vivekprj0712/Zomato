package com.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.Entity.CustomerEntity;
import com.Entity.RestaurantEntity;
import com.Entity.RoleEntity;
import com.Repository.CustomerRepository;
import com.Repository.RestaurantRepository;
import com.Repository.RoleRepository;
import com.Service.MailerService;

import jakarta.mail.Session;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



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
	
	@GetMapping("authentication/{email}/{password}") // some problem pending....
	public String authentication(@PathVariable("email") String email , @PathVariable("password") String password ,CustomerEntity customerEntity, RestaurantEntity restaurantEntity , HttpSession session) {
		CustomerEntity loggedInCustomer = customerRepository.findByEmail(email);
		RestaurantEntity loggedInRestaurant = restaurantRepository.findByEmail(email);
		if(loggedInCustomer == null && loggedInRestaurant == null) {
			return null;
		}else {
			
			if(loggedInCustomer != null) {
				session.setMaxInactiveInterval(60*60); 
				session.setAttribute("customer", loggedInCustomer); //set student into session 
				
				if(!loggedInCustomer.getPassword().matches(password)) {
					return null;
				}else if(loggedInCustomer.getRole() == null) {
					return null;
				}else if(loggedInCustomer.getRole() == customerEntity.getRole()) {
					System.out.println(loggedInCustomer.getRole());
					System.out.println(customerEntity.getRole());
					return "Customer";	
				}
			}else if(loggedInRestaurant != null) {
					session.setMaxInactiveInterval(60*60); 
					session.setAttribute("restaurant", loggedInRestaurant); //set student into session 
					
					if(!loggedInRestaurant.getPassword().matches(password)) {
						return null;
					}else if(loggedInRestaurant.getRole() == null) {
						return null;
					}else if(loggedInRestaurant.getRole() == restaurantEntity.getRole()) {
						System.out.println(loggedInRestaurant.getRole());
						System.out.println(restaurantEntity.getRole());
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
	
	@GetMapping("updatepassword/{email}/{password}/{confirmpassword}/{otp}") // some problem pending....
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
			if( otp == -1 || dbCustomer.getOtp() != otp || dbCustomer.getOtp() == null)
			{
				System.out.println("error...3");
				System.out.println(otp);
				System.out.println(dbCustomer.getOtp());
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
