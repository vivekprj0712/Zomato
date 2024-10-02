package com.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.RoleEntity;
import com.Repository.RoleRepository;

import jakarta.persistence.criteria.CriteriaBuilder.In;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class RoleController {

	@Autowired
	RoleRepository roleRepository;
	
	//Add Role
	@PostMapping("roles")
	public RoleEntity addRole(@RequestBody RoleEntity roleEntity) {
	
		roleRepository.save(roleEntity);
		return roleEntity;
	}
	
	//Read All Roles
	@GetMapping("roles")
	public List<RoleEntity> getAllRoles() {
		List<RoleEntity> roles = roleRepository.findAll();
		return roles;
	}
	
	//Read role By Id
	@GetMapping("roles/{roleId}")
	public RoleEntity getRoleById(@PathVariable("roleId") Integer roleId) {
		Optional<RoleEntity> role = roleRepository.findById(roleId);
		if(role.isEmpty()) {
			return null;
		}else {			
			RoleEntity roleEntity = role.get();
			return roleEntity;
		}
	}
	
	//delete Role By Id
	@DeleteMapping("roles/{roleId}")
	public RoleEntity deleteRole(@PathVariable("roleId") Integer roleId, RoleEntity roleEntity) {
		Optional<RoleEntity> role = roleRepository.findById(roleId);
		if(role.isEmpty()) {
			return null;
		}else {			
			roleRepository.deleteById(roleId);
			return roleEntity;
		}
	}
	
	//Update Role By Id
	@PutMapping("roles/{roleId}")
	public RoleEntity updateRole(@PathVariable("roleId") Integer roleId, @RequestBody RoleEntity roleEntity) {
		
		Optional<RoleEntity> role = roleRepository.findById(roleId);
		if(role.isEmpty()) {
			return null;
		}else {			
			roleRepository.save(roleEntity);
			return roleEntity;
		}
		
	}
	
}
