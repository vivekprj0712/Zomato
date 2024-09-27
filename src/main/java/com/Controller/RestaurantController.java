package com.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.RestaurantEntity;
import com.Repository.RestaurantRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class RestaurantController {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	//add Restaurant
	@PostMapping("restaurants")
	public RestaurantEntity addRestaurant(@RequestBody RestaurantEntity restaurantEntity) {
	
		restaurantRepository.save(restaurantEntity);
		return restaurantEntity;
	}
	
	// Read All Restaurant
	@GetMapping("restaurants")
	public List<RestaurantEntity> getAllRestaurants() {
		List<RestaurantEntity> restaurants = restaurantRepository.findAll();
		if(restaurants.isEmpty()) {
			return null;
		}else {
			return restaurants;
		}
	}
	
	// Read Restaurant by Id
		@GetMapping("restaurants/{restaurantId}")
		public RestaurantEntity getRestaurantsById(@PathVariable("restaurantId") Integer restaurantId) {
			Optional<RestaurantEntity> restaurants = restaurantRepository.findById(restaurantId);
			if(restaurants.isEmpty()) {
				return null;
			}else {
				RestaurantEntity	restaurantEntity = restaurants.get();
				return restaurantEntity;
			}
		}
	
	// Read All Restaurant By Active
	@GetMapping("restaurantsbyactive/{active}")
	public List<RestaurantEntity> getAllRestaurantsByActive(@PathVariable("active") Integer active ) {
		List<RestaurantEntity> restaurants = restaurantRepository.findByActive(active);
		if(restaurants.isEmpty()) {
			return null;
		}else {
			
			return restaurants;
		}
	}

	// Read All Restaurant By Active and Pincode
	@GetMapping("restaurants/{active}/{pincode}")
	public List<RestaurantEntity> getAllRestaurantsByActive(@PathVariable("active") Integer active , @PathVariable("pincode") Integer pincode) {
		List<RestaurantEntity> restaurants = restaurantRepository.findByActiveAndPincode(active,pincode);
		if(restaurants.isEmpty()) {
			return null;
		}else {
			return restaurants;
		}
	}
	
	// Delete Restaurant By Id
	@DeleteMapping("restaurants/{restaurantId}")
	public RestaurantEntity deleteRestaurantById(@PathVariable("restaurantId") Integer restaurantId , RestaurantEntity restaurantEntity) {
		Optional<RestaurantEntity> restaurant = restaurantRepository.findById(restaurantId);
		if(restaurant.isEmpty()) {
			return null;
		}else {
			restaurantRepository.deleteById(restaurantId);
			return restaurantEntity;
		}
	}
	
	// Update Restaurant by Id
	@PutMapping("restaurants/{restaurantId}")
	public RestaurantEntity updateRestaurant(@PathVariable("restaurantId") Integer restaurantId, @RequestBody RestaurantEntity restaurantEntity) {
			restaurantRepository.save(restaurantEntity);
			return restaurantEntity;
		}
}
