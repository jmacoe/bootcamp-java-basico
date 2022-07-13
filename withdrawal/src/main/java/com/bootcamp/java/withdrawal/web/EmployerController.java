package com.bootcamp.java.withdrawal.web;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.java.withdrawal.web.model.EmployerModel;
import com.bootcamp.java.withdrawal.service.IEmployerService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller for Employer Service
 * @author jmacoele
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/Employer")
@Slf4j
public class EmployerController {
	
	@Autowired
	private final IEmployerService employerService;

	/**
	 * Get list of Employers
	 * @return
	 * @throws Exception
	 */
	@GetMapping()	
	@Operation(summary = "Get list of Employers")
	public ResponseEntity<Object> getAll() throws Exception {
		List<EmployerModel> response =  employerService.findAll();
		log.info("getAll" + "OK");
		log.debug(response.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Get Employer by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = { "{id}" }, produces = { "application/json" })
	@Operation(summary = "Get Employer by id")
	public ResponseEntity<EmployerModel> getById(@PathVariable("id") Long id) throws Exception{
		EmployerModel response = employerService.findById(id);
		log.info("getById" + "OK");
		log.debug(id.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Create Employer
	 * @param employerModel
	 * @return
	 * @throws Exception
	 */
	@PostMapping()
	@Operation(summary = "Create Employer")
	public ResponseEntity<Object> create(@Valid @RequestBody EmployerModel employerModel) throws Exception {
		EmployerModel response = employerService.create(employerModel);
		log.info("create" + "OK");
		log.debug(employerModel.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Update Employer by id
	 * @param id
	 * @param employerModel
	 * @throws Exception
	 */
	@PutMapping(path = { "{id}" }, produces = { "application/json" })
	@Operation(summary = "Update Employer by id")
	public void update(		
			@PathVariable("id") Long id,
			@Valid @RequestBody EmployerModel employerModel) throws Exception {		
		employerService.update(id, employerModel);
		log.info("update" + "OK");
		log.debug(id.toString()+ "/" + employerModel.toString());
	}
	
	/**
	 * Delete Employer by id
	 * @param id
	 * @throws Exception
	 * @author jmacoele
	 */
	@DeleteMapping({ "{id}" })
	@Operation(summary = "Delete Employer by id")
	public void deleteById(@PathVariable("id") Long id) throws Exception {
		employerService.deleteById(id);
		log.info("deleteById" + "OK");
		log.debug(id.toString());
	}
}
