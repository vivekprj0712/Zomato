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
@Table(name="Customers")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer customerId;
	String firstName;
	String lastName;
	String email;
	String password;
	String profilePicPath;
	Integer otp;
	String gender;
	Integer bornYear;
	String contactNum;
	
	@OneToMany(mappedBy = "customer")
	List<CustomerAddressEntity> customerAddress;
	
	@ManyToOne
	@JoinColumn(name = "roleId")
	RoleEntity role;
	
}
