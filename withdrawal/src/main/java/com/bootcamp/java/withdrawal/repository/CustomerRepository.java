package com.bootcamp.java.withdrawal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.java.withdrawal.domain.Customer;


/** Repository class for Customer entity
 * @author jmacoele
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	/**
	 * Search a Customer by DNI
	 * @param dni
	 * @return
	 */
	List<Customer> findByDni(String dni);
	
}
