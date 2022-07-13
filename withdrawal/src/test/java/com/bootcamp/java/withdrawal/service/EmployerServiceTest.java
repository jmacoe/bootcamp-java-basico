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

import com.bootcamp.java.withdrawal.domain.Employer;
import com.bootcamp.java.withdrawal.repository.EmployerRepository;
import com.bootcamp.java.withdrawal.service.mapper.EmployerMapper;
import com.bootcamp.java.withdrawal.web.model.EmployerModel;

@SpringBootTest(classes = {EmployerMapper.class})
class EmployerServiceTest {
 
	@Mock
	EmployerRepository employerRepository;
	
	@Spy
	private EmployerMapper employerMapper = Mappers.getMapper(EmployerMapper.class);
	 
	
	EmployerService employerService;
	
	@BeforeEach
	void beforeEach() {
		employerService = new EmployerService(employerRepository, employerMapper);
	}
		
	@Test
	void create() throws Exception 
	{
		 //given
		Employer employer = Employer
				.builder()
				.ruc("20603459823")
				.companyName("DevTech Industries")
				.build();
		
		 given(employerRepository.save(any(Employer.class))).willReturn(employer);
		 
		 //when		
		 EmployerModel employersToCreate = employerMapper.employerToEmployerModel(employer);
	     EmployerModel employersCreated = employerService.create(employersToCreate);
	     
	     //then
	     then(employerRepository).should().save(any(Employer.class));
	     assertThat(employersCreated).isNotNull();
	}
	
	@Test
	void update() throws Exception 
	{
		 //given
		Employer employer = Employer
				.builder()
				.ruc("20603459823")
				.companyName("DevTech Industries")
				.build();
		 given(employerRepository.findById(Long.valueOf(1))).willReturn(Optional.of(employer));
	 
		 //when		
		 EmployerModel employersToUpdate = employerMapper.employerToEmployerModel(employer);
	     employerService.update(Long.valueOf(1), employersToUpdate);
	     
	     //then
	     then(employerRepository).should().save(any(Employer.class));
	}
	
	@Test
	void updateNotFound() throws Exception 
	{
		Employer employer = Employer
				.builder()
				.ruc("20603459823")
				.companyName("DevTech Industries")
				.build();
		 EmployerModel employersToUpdate = employerMapper.employerToEmployerModel(employer);
		    
		 assertThrows(Exception.class, () -> employerService.update(Long.valueOf(1), employersToUpdate));
	}
		
	@Test
	void findAll() throws Exception 
	{
		 //given
		 List<Employer> employers = new ArrayList<Employer>();
		 employers.add(Employer
					.builder()
					.ruc("20603459823")
					.companyName("DevTech Industries")
					.build());
		 
		 employers.add(Employer
					.builder()
					.ruc("20603459823")
					.companyName("SinCity Industries")
					.build());
		 
		 given(employerRepository.findAll()).willReturn(employers);
		 
		 //when
	     List<EmployerModel> employersFindAll = employerService.findAll();
	     
	     //then
	     then(employerRepository).should().findAll();
	     assertThat(employersFindAll).isNotNull();
	}
	
	@Test
	void findById() throws Exception 
	{
		 //given
		 Employer employer = new Employer();
		 given(employerRepository.findById(Long.valueOf(1))).willReturn(Optional.of(employer));
		 
		 //when
	     EmployerModel employersFindById = employerService.findById(Long.valueOf(1));
	     
	     //then
	     then(employerRepository).should().findById(Long.valueOf(1));
	     assertThat(employersFindById).isNotNull();
	}
	
	@Test
	void findByIdNotFound() throws Exception 
	{
		 assertThrows(Exception.class, () -> employerService.findById(Long.valueOf(1)));
	}
		
	@Test
	void deleteById() throws Exception{
		 //given
		 
		 //when		
	     employerService.deleteById(Long.valueOf(1));
	     
	     //then
	     then(employerRepository).should().deleteById(anyLong());
	}
	
}
