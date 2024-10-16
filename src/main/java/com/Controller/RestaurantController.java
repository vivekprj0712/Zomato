package com.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.RestaurantEntity;
import com.Entity.RoleEntity;
import com.Repository.RestaurantRepository;
import com.Repository.RoleRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/private/restaurant")
public class RestaurantController {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	
	// Read All Restaurant
	@GetMapping("restaurants")
	public ResponseEntity<?> getAllRestaurants() {
		List<RestaurantEntity> restaurants = restaurantRepository.findAll();
		if(restaurants.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(restaurants,HttpStatus.OK);
		}
	}
	
	// Read Restaurant by Id
		@GetMapping("restaurants/{restaurantId}")
		public ResponseEntity<?> getRestaurantsById(@PathVariable("restaurantId") Integer restaurantId) {
			Optional<RestaurantEntity> restaurants = restaurantRepository.findById(restaurantId);
			if(restaurants.isEmpty()) {
				return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
			}else {
				RestaurantEntity	restaurantEntity = restaurants.get();
				return new ResponseEntity<>(restaurantEntity,HttpStatus.OK);
			}
		}
	
	// Read All Restaurant By Active
	@GetMapping("restaurantsbyactive/{active}")
	public ResponseEntity<?> getAllRestaurantsByActive(@PathVariable("active") Integer active ) {
		List<RestaurantEntity> restaurants = restaurantRepository.findByActive(active);
		if(restaurants.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(restaurants,HttpStatus.OK);
		}
	}

	// Read All Restaurant By Active and PinCode
	@GetMapping("restaurants/{active}/{pincode}")
	public ResponseEntity<?> getAllRestaurantsByActive(@PathVariable("active") Integer active , @PathVariable("pincode") Integer pincode) {
		List<RestaurantEntity> restaurants = restaurantRepository.findByActiveAndPincode(active,pincode);
		if(restaurants.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(restaurants,HttpStatus.OK);
		}
	}
	
	// Delete Restaurant By Id
	@DeleteMapping("restaurants/{restaurantId}")
	public ResponseEntity<?> deleteRestaurantById(@PathVariable("restaurantId") Integer restaurantId) {
		Optional<RestaurantEntity> restaurant = restaurantRepository.findById(restaurantId);
		if(restaurant.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
		}else {
			restaurantRepository.deleteById(restaurantId);
			return new ResponseEntity<>("Record Deleted Successfully",HttpStatus.OK);
		}
	}
	
	// Update Restaurant by Id
	@PutMapping("restaurants/{restaurantId}")
	public ResponseEntity<String> updateRestaurant(@PathVariable("restaurantId") Integer restaurantId, @RequestBody RestaurantEntity restaurantEntity) {
			restaurantRepository.save(restaurantEntity);
			return new ResponseEntity<>("Record Updated Successfully",HttpStatus.OK);
		}
}
