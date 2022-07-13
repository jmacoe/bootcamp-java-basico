package com.bootcamp.java.withdrawal.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.bootcamp.java.withdrawal.domain.Customer;
import com.bootcamp.java.withdrawal.repository.CustomerRepository;
import com.bootcamp.java.withdrawal.service.mapper.CustomerMapper;
import com.bootcamp.java.withdrawal.web.model.CustomerModel;

@SpringBootTest(classes = {CustomerMapper.class})
class CustomerServiceTest {
 
	@Mock
	CustomerRepository customerRepository;
	
	@Spy
	private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
	 
	
	CustomerService customerService;
	
	@BeforeEach
	void beforeEach() {
		customerService = new CustomerService(customerRepository, customerMapper);
	}
		
	@Test
	void create() throws Exception 
	{
		 //given
		Customer customer = Customer
				.builder()
				.id(Long.valueOf(100))
				.dni("12345678")
				.firstname("Max")
				.lastname("Planck")
				.afp("INTEGRA")
				.email("jmacoe@gmail.com")
				.phoneNumber("943133590")
				.build();
		
		 given(customerRepository.save(any(Customer.class))).willReturn(customer);
		 
		 //when		
		 CustomerModel customersToCreate = customerMapper.customerToCustomerModel(customer);
	     CustomerModel customersCreated = customerService.create(customersToCreate);
	     
	     //then
	     then(customerRepository).should().save(any(Customer.class));
	     assertThat(customersCreated).isNotNull();
	}
	
	@Test
	void update() throws Exception 
	{
		 //given
		Customer customer = Customer
				.builder()
				.id(Long.valueOf(1))
				.dni("12345678")
				.firstname("Max")
				.lastname("Planck")
				.afp("INTEGRA")
				.email("jmacoe@gmail.com")
				.phoneNumber("943133590")
				.build();
		 given(customerRepository.findById(Long.valueOf(1))).willReturn(Optional.of(customer));
	 
		 //when		
		 CustomerModel customersToUpdate = customerMapper.customerToCustomerModel(customer);
	     customerService.update(Long.valueOf(1), customersToUpdate);
	     
	     //then
	     then(customerRepository).should().save(any(Customer.class));
	}
	
	@Test
	void updateNotFound() throws Exception 
	{
		Customer customer = Customer
				.builder()
				.id(Long.valueOf(100))
				.dni("12345678")
				.firstname("Max")
				.lastname("Planck")
				.afp("PROFUTURO")
				.email("jmacoe@gmail.com")
				.phoneNumber("943133590")
				.build();
		 CustomerModel customersToUpdate = customerMapper.customerToCustomerModel(customer);
		    
		 assertThrows(Exception.class, () -> customerService.update(Long.valueOf(1), customersToUpdate));
	}
		
	@Test
	void findAll() throws Exception 
	{
		 //given
		 List<Customer> customers = new ArrayList<Customer>();
		 customers.add(Customer
					.builder()
					.id(Long.valueOf(100))
					.dni("12345678")
					.firstname("Max")
					.lastname("Planck")
					.afp("PROFUTURO")
					.email("jmacoe@gmail.com")
					.phoneNumber("943133590")
					.build());
		 
		 customers.add(Customer
					.builder()
					.id(Long.valueOf(100))
					.dni("12345679")
					.firstname("Julio")
					.lastname("Verne")
					.afp("INTEGRA")
					.email("jmacoe@gmail.com")
					.phoneNumber("943133590")
					.build());
		 
		 given(customerRepository.findAll()).willReturn(customers);
		 
		 //when
	     List<CustomerModel> customersFindAll = customerService.findAll();
	     
	     //then
	     then(customerRepository).should().findAll();
	     assertThat(customersFindAll).isNotNull();
	}
	
	@Test
	void findById() throws Exception 
	{
		 //given
		 Customer customer = new Customer();
		 given(customerRepository.findById(Long.valueOf(1))).willReturn(Optional.of(customer));
		 
		 //when
	     CustomerModel customersFindById = customerService.findById(Long.valueOf(1));
	     
	     //then
	     then(customerRepository).should().findById(Long.valueOf(1));
	     assertThat(customersFindById).isNotNull();
	}
	
	@Test
	void findByIdNotFound() throws Exception 
	{
		 assertThrows(Exception.class, () -> customerService.findById(Long.valueOf(1)));
	}
	
	@Test
	void findByDni() throws Exception
	{
		 //given
		 List<Customer> customers = new ArrayList<Customer>();
		 customers.add(Customer
					.builder()
					.id(Long.valueOf(100))
					.dni("12345678")
					.firstname("Max")
					.lastname("Planck")
					.afp("PROFUTURO")
					.email("jmacoe@gmail.com")
					.phoneNumber("943133590")
					.build());
	
		 
		 given(customerRepository.findByDni(
				 "12345678"))
				 .willReturn(customers);
		 
		 //when
	     List<CustomerModel> customersFindAll = customerService.findByDni(
	    		 "40853585");
	     
	     //then
	     then(customerRepository).should().findByDni(
	    		 "40853585");
	     assertThat(customersFindAll).isNotNull();
	}
		
	@Test
	void deleteById() throws Exception{
		 //given
		 
		 //when		
	     customerService.deleteById(Long.valueOf(1));
	     
	     //then
	     then(customerRepository).should().deleteById(anyLong());
	}
	
}
