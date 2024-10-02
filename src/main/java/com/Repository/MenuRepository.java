package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.MenuEntity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Integer>{

	List<MenuEntity> findByActive(Integer active);

//	List<MenuEntity> findByRestaurantIdAndActive(Integer restaurantId, Integer active);
	
}
