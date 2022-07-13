package com.bootcamp.java.withdrawal.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bootcamp.java.withdrawal.domain.Employer;
import com.bootcamp.java.withdrawal.service.mapper.EmployerMapper;
import com.bootcamp.java.withdrawal.web.model.EmployerModel;
import com.bootcamp.java.withdrawal.repository.EmployerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployerService implements IEmployerService {

	private final EmployerRepository employerRepository;
	private final EmployerMapper employerMapper;

	
	@Override
	public List<EmployerModel> findAll()  throws Exception {
		List<Employer> employers = employerRepository.findAll();
		return employerMapper.employersToEmployerModels(employers);
	}
	
	@Override
	public EmployerModel findById(Long id) throws Exception {
		Optional<Employer> employer = employerRepository.findById(id);
		if(employer.isPresent())	return employerMapper.employerToEmployerModel(employer.get());
		else throw new Exception("No se encontraron datos");
	}

	@Override
	public EmployerModel create(EmployerModel employerModel)  throws Exception{
		Employer employer = employerRepository.save(employerMapper.employerModelToEmployer(employerModel));
		return employerMapper.employerToEmployerModel(employer);
	}

	@Override
	public void update(Long id, EmployerModel employerModel)  throws Exception {
		Optional<Employer> employerOptional = employerRepository.findById(id);
		
		if(employerOptional.isPresent()) {
			Employer employerToUpdate = employerOptional.get();
			employerMapper.update(employerToUpdate, employerModel);
			employerRepository.save(employerToUpdate);
		}
		else throw new Exception("No se encontraron datos");
	}

	@Override
	public void deleteById(Long id)  throws Exception {
		employerRepository.deleteById(id);
	}

}
