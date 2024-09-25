package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.CustomerAddressEntity;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddressEntity, Integer>{

}
