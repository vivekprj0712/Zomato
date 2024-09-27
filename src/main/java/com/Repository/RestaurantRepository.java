package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.RestaurantEntity;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer>{

	List<RestaurantEntity> findByActive(Integer active);

	List<RestaurantEntity> findByActiveAndPincode(Integer active, Integer pincode);
}
