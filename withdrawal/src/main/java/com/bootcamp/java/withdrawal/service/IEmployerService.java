package com.bootcamp.java.withdrawal.service;

import java.util.List;

import com.bootcamp.java.withdrawal.web.model.EmployerModel;

public interface IEmployerService {
	List<EmployerModel> findAll() throws Exception;
	
	EmployerModel findById(Long id) throws Exception;
	
	EmployerModel create(EmployerModel employerModel) throws Exception;

	void update(Long id, EmployerModel employerModel) throws Exception;

	void deleteById(Long id) throws Exception;
}
