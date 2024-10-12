package com.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.ItemEntity;
import com.Repository.ItemRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class ItemController {

	@Autowired
	ItemRepository itemRepository;
	
	//add item
	@PostMapping("items")
	public ItemEntity addItem(@RequestBody ItemEntity itemEntity) {	
		itemRepository.save(itemEntity);
		return itemEntity;
	}
	
	//get All Items
	@GetMapping("items")
	public List<ItemEntity> getAllItems() {
		List<ItemEntity> items = itemRepository.findAll();
		if(items.isEmpty()) {
			return null;
		}else {
			return items;
		}
	}
	
	//get Items by Id
		@GetMapping("items/{itemId}")
		public ItemEntity getItemById(@PathVariable("itemId") Integer itemId) {
			Optional<ItemEntity> items = itemRepository.findById(itemId);
			if(items.isEmpty()) {
				return null;
			}else {
				ItemEntity item = items.get();
				return item;
			}
		}
	
		//get Items by Menu Id
		@GetMapping("itembymenuid/{menuId}")
		public ItemEntity getItemByMenuId(@PathVariable("menuId") Integer menuId) {
			Optional<ItemEntity> items = itemRepository.findById(menuId);
			if(items.isEmpty()) {
				return null;
			}else {
				ItemEntity item = items.get();
				return item;
			}
		}
		//delete Items by Id
		@DeleteMapping("items/{itemId}")
		public ItemEntity deleteItemById(@PathVariable("itemId") Integer itemId , ItemEntity itemEntity) {
			Optional<ItemEntity> item = itemRepository.findById(itemId);
			if(item.isEmpty()) {
				return null;
			}else {
				itemRepository.deleteById(itemId);
				return itemEntity;
			}
		}
	
		//update Items by Id
		@PutMapping("items/{itemId}")
		public ItemEntity updateItemById(@PathVariable("itemId") Integer itemId ,@RequestBody ItemEntity itemEntity) {
			itemRepository.save(itemEntity);
			return itemEntity;
		}
}
