package com.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "Restaurants")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer restaurantId;
	String title;
	String category;
	String description;
	String timings;
	String address;
	String contactNum;
	Float latitude;
	Float longitude;
	Integer pincode;
	Integer online;
	String email;
	String password;
	Integer active;
	String restaurantImagePath;

	@OneToMany(mappedBy = "restaurant")
	List<MenuEntity> menuEntity;

	@ManyToOne
	@JoinColumn(name = "roleId")
	RoleEntity role;
}
