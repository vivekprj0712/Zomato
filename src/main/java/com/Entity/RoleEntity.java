package com.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "Roles")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer roleId;
	String roleName;
	
	@OneToMany(mappedBy = "role")
	List<CustomerEntity> customerEntity;
	
	@OneToMany(mappedBy = "role")
	List<RestaurantEntity> restaurantEntity;
}
