package com.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.MenuEntity;
import com.Repository.MenuRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/private/menu")
public class MenuController {

	@Autowired
	MenuRepository menuRepository;
	
	// add menu
	@PostMapping("menus")
	public ResponseEntity<String> addMenu(@RequestBody MenuEntity menuEntity) {
		menuRepository.save(menuEntity);
		return new ResponseEntity<>("Record Added Successfully",HttpStatus.CREATED);
	}
	
	//red All menus
	@GetMapping("menus")
	public ResponseEntity<?> getAllMenus() {
		List<MenuEntity> menus = menuRepository.findAll();
		if(menus.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(menus,HttpStatus.OK);
		}
	}
	
	//read Menu By Id
	@GetMapping("menus/{menuId}")
	public ResponseEntity<?> getMenuById(@PathVariable("menuId") Integer menuId) {
		Optional<MenuEntity> menu = menuRepository.findById(menuId);
		if (menu.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
		}else {
			MenuEntity menuEntity = menu.get();
			return new ResponseEntity<>(menuEntity,HttpStatus.OK);
		}
	}
	
	//read menu By RestaurantId
	@GetMapping("menubyrestaurantid/{restaurantId}")
	public ResponseEntity<?> getMenuByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) {
		Optional<MenuEntity> menus = menuRepository.findById(restaurantId);
		if (menus.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(menus,HttpStatus.OK);
		}
	}
	
//	@GetMapping("menus/{restaurantId}/{active}")
//	public List<MenuEntity> getMenuByRestaurantIdAndActive(@PathVariable("restaurantId") Integer restaurantId, @PathVariable("active") Integer active) {
//		List<MenuEntity> menus = menuRepository.findByRestaurantIdAndActive(restaurantId,active);
//		if (menus.isEmpty()) {
//			return null;
//		}else {
//			return menus;
//		}
//	}	

	//read menu by Active
	@GetMapping("menubyactive/{active}")
	public ResponseEntity<?> getMenuByActive(@PathVariable("active") Integer active) {
		List<MenuEntity> menus = menuRepository.findByActive(active);
		if (menus.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(menus,HttpStatus.OK);

		}
	}
	
	//delete Menu by Id
	@DeleteMapping("menus/{menuId}")
	public ResponseEntity<String> deleteMenu(@PathVariable("menuId") Integer menuId , MenuEntity menuEntity) {
		Optional<MenuEntity> menu = menuRepository.findById(menuId);
		if(menu.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
		}else {
			menuRepository.deleteById(menuId);
			return new ResponseEntity<>("Record Deleted Successfully",HttpStatus.OK);
		}
	}
	
	//update menu by Id
	@PutMapping("menus/{menuId}")
	public ResponseEntity<String> updateMenu(@PathVariable("menuId") Integer menuId , @RequestBody MenuEntity menuEntity) {
		
			menuRepository.save(menuEntity);
			return new ResponseEntity<>("Record Updated Successfully",HttpStatus.OK);
	}
}
