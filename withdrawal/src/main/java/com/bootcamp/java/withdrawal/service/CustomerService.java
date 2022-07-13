package com.bootcamp.java.withdrawal.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bootcamp.java.withdrawal.domain.Customer;
import com.bootcamp.java.withdrawal.service.mapper.CustomerMapper;
import com.bootcamp.java.withdrawal.web.model.CustomerModel;
import com.bootcamp.java.withdrawal.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;

	
	@Override
	public List<CustomerModel> findAll()  throws Exception {
		List<Customer> customers = customerRepository.findAll();
		return customerMapper.customersToCustomerModels(customers);
	}
	
	@Override
	public CustomerModel findById(Long id) throws Exception {
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isPresent())	return customerMapper.customerToCustomerModel(customer.get());
		else throw new Exception("No se encontraron datos");
	}

	@Override
	public List<CustomerModel> findByDni(String dni) throws Exception{
		List<Customer> customers = customerRepository.findByDni(dni);
		return customerMapper.customersToCustomerModels(customers);
	}
	 
	@Override
	public CustomerModel create(CustomerModel customerModel)  throws Exception{
		
		/*** 
		 * Verificamos que otro cliente no este registrado con el mismo dni,
		 * con esto aseguramos que el cliente solo este en una afp actual
		 */
		
		List<Customer> customers = customerRepository
				.findByDni(customerModel.getDni());
		
		if (!customers.isEmpty()) throw new Exception("El cliente ya esta registrado");
		
		Customer customer = customerRepository.save(customerMapper.customerModelToCustomer(customerModel));
		return customerMapper.customerToCustomerModel(customer);
	}

	@Override
	public void update(Long id, CustomerModel customerModel)  throws Exception {
		Optional<Customer> customerOptional = customerRepository.findById(id);
		
		if(customerOptional.isPresent()) {
			Customer customerToUpdate = customerOptional.get();
			customerMapper.update(customerToUpdate, customerModel);
			customerRepository.save(customerToUpdate);
		}
		else throw new Exception("No se encontraron datos");
	}

	@Override
	public void deleteById(Long id)  throws Exception {
		customerRepository.deleteById(id);
	}

}
