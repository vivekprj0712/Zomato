package com.Controller;

import java.util.List;
import java.util.Optional;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.RoleEntity;
import com.Repository.RoleRepository;

import jakarta.persistence.criteria.CriteriaBuilder.In;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/private/role")
public class RoleController {

	@Autowired
	RoleRepository roleRepository;
	
	//Add Role
	@PostMapping("roles")
	public ResponseEntity<String> addRole(@RequestBody RoleEntity roleEntity) {
	
		roleRepository.save(roleEntity);
		return new ResponseEntity<>("Record Added successfully",HttpStatus.CREATED);
	}
	
	//Read All Roles
	@GetMapping("roles")
	public ResponseEntity<?> getAllRoles() {
		List<RoleEntity> roles = roleRepository.findAll();
		if (roles.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(roles,HttpStatus.OK);
		}
	}
	
	//Read role By Id
	@GetMapping("roles/{roleId}")
	public ResponseEntity<?> getRoleById(@PathVariable("roleId") Integer roleId) {
		Optional<RoleEntity> role = roleRepository.findById(roleId);
		if(role.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
		}else {			
			RoleEntity roleEntity = role.get();
			return new ResponseEntity<>(roleEntity,HttpStatus.OK);
		}
	}
	
	//delete Role By Id
	@DeleteMapping("roles/{roleId}")
	public ResponseEntity<String> deleteRole(@PathVariable("roleId") Integer roleId, RoleEntity roleEntity) {
		Optional<RoleEntity> role = roleRepository.findById(roleId);
		if(role.isEmpty()) {
			return new ResponseEntity<>("Record not found",HttpStatus.NOT_FOUND);
		}else {			
			roleRepository.deleteById(roleId);
			return new ResponseEntity<>("Record Deleted Successfully",HttpStatus.OK);
		}
	}
	
	//Update Role By Id
	@PutMapping("roles/{roleId}")
	public ResponseEntity<?> updateRole(@PathVariable("roleId") Integer roleId, @RequestBody RoleEntity roleEntity) {
				
			roleRepository.save(roleEntity);
			return new ResponseEntity<>("Record Updated Successfully",HttpStatus.OK);
		
	}
	
}
