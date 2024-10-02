package com.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.MenuEntity;
import com.Repository.MenuRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class MenuController {

	@Autowired
	MenuRepository menuRepository;
	
	// add menu
	@PostMapping("menus")
	public MenuEntity addMenu(@RequestBody MenuEntity menuEntity) {
		menuRepository.save(menuEntity);
		return menuEntity;
	}
	
	//red All menus
	@GetMapping("menus")
	public List<MenuEntity> getAllMenus() {
		List<MenuEntity> menus = menuRepository.findAll();
		return menus;
	}
	
	//read Menu By Id
	@GetMapping("menus/{menuId}")
	public MenuEntity getMenuById(@PathVariable("menuId") Integer menuId) {
		Optional<MenuEntity> menu = menuRepository.findById(menuId);
		if (menu.isEmpty()) {
			return null;
		}else {
			MenuEntity menuEntity = menu.get();
			return menuEntity;
		}
	}
	
	//read menu By RestaurantId
	@GetMapping("menubyrestaurantid/{restaurantId}")
	public Optional<MenuEntity> getMenuByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) {
		Optional<MenuEntity> menus = menuRepository.findById(restaurantId);
		if (menus.isEmpty()) {
			return null;
		}else {
			return menus;
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
	public List<MenuEntity> getMenuByActive(@PathVariable("active") Integer active) {
		List<MenuEntity> menus = menuRepository.findByActive(active);
		if (menus.isEmpty()) {
			return null;
		}else {
			return menus;
		}
	}
	
	//delete Menu by Id
	@DeleteMapping("menus/{menuId}")
	public MenuEntity deleteMenu(@PathVariable("menuId") Integer menuId , MenuEntity menuEntity) {
		Optional<MenuEntity> menu = menuRepository.findById(menuId);
		if(menu.isEmpty()) {
			return null;
		}else {
			menuRepository.deleteById(menuId);
			return menuEntity;
		}
	}
	
	//update menu by Id
	@PutMapping("menus/{menuId}")
	public MenuEntity updateMenu(@PathVariable("menuId") Integer menuId , @RequestBody MenuEntity menuEntity) {
		Optional<MenuEntity> menu = menuRepository.findById(menuId);
		if(menu.isEmpty()) {
			return null;
		}else {
			menuRepository.save(menuEntity);
			return menuEntity;
		}
	}
}
