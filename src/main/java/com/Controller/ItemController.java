package com.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.ItemEntity;
import com.Repository.ItemRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/private/item")
public class ItemController {

	@Autowired
	ItemRepository itemRepository;
	
	//add item
	@PostMapping("items")
	public ResponseEntity<String> addItem(@RequestBody ItemEntity itemEntity) {	
		itemRepository.save(itemEntity);
		return new ResponseEntity<>("Record Added Successfully",HttpStatus.CREATED);
	}
	
	//get All Items
	@GetMapping("items")
	public ResponseEntity<?> getAllItems() {
		List<ItemEntity> items = itemRepository.findAll();
		if(items.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(items,HttpStatus.OK);
		}
	}
	
	//get Items by Id
		@GetMapping("items/{itemId}")
		public ResponseEntity<?> getItemById(@PathVariable("itemId") Integer itemId) {
			Optional<ItemEntity> items = itemRepository.findById(itemId);
			if(items.isEmpty()) {
				return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
			}else {
				ItemEntity item = items.get();
				return new ResponseEntity<>(item,HttpStatus.OK);
			}
		}
	
		//get Items by Menu Id
		@GetMapping("itembymenuid/{menuId}")
		public ResponseEntity<?> getItemByMenuId(@PathVariable("menuId") Integer menuId) {
			Optional<ItemEntity> items = itemRepository.findById(menuId);
			if(items.isEmpty()) {
				return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
			}else {
				ItemEntity item = items.get();
				return new ResponseEntity<>(item,HttpStatus.OK);
			}
		}
		//delete Items by Id
		@DeleteMapping("items/{itemId}")
		public ResponseEntity<String> deleteItemById(@PathVariable("itemId") Integer itemId , ItemEntity itemEntity) {
			Optional<ItemEntity> item = itemRepository.findById(itemId);
			if(item.isEmpty()) {
				return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
			}else {
				itemRepository.deleteById(itemId);
				return new ResponseEntity<>("Record Deleted Successfully",HttpStatus.OK);
			}
		}
	
		//update Items by Id
		@PutMapping("items/{itemId}")
		public ResponseEntity<String> updateItemById(@PathVariable("itemId") Integer itemId ,@RequestBody ItemEntity itemEntity) {
			itemRepository.save(itemEntity);
			return new ResponseEntity<>("Record Updated Successfully",HttpStatus.OK);
		}
}
