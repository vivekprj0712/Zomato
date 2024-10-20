package com.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{
			
	CustomerEntity findByEmail(String email);
	Optional<CustomerEntity> findByAuthToken(String authToken);

}
