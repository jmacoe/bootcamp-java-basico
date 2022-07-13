package com.bootcamp.java.withdrawal.service;

import java.util.List;

import com.bootcamp.java.withdrawal.web.model.CustomerModel;

public interface ICustomerService {

	List<CustomerModel> findAll() throws Exception;
	
	CustomerModel findById(Long id) throws Exception;
	
	List<CustomerModel> findByDni(String dni) throws Exception;
	
	CustomerModel create(CustomerModel customerModel) throws Exception;

	void update(Long id, CustomerModel customerModel) throws Exception;

	void deleteById(Long id) throws Exception;
}
