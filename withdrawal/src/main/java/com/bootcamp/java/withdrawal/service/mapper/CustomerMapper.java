package com.bootcamp.java.withdrawal.service.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.bootcamp.java.withdrawal.domain.Customer;
import com.bootcamp.java.withdrawal.web.model.CustomerModel;

@Mapper(componentModel = "spring", uses = {BalanceMapper.class, EmployerMapper.class, RequestMapper.class})
public interface CustomerMapper {

	 Customer customerModelToCustomer (CustomerModel model);
	 
	 CustomerModel customerToCustomerModel (Customer customer);
	 
	 List<CustomerModel> customersToCustomerModels(List<Customer> customers);
	
	 
	 @Mapping(target = "id", ignore = true)
	 void update(@MappingTarget Customer entity, CustomerModel updateEntity);
}
