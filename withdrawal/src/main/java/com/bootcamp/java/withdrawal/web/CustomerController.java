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

import com.bootcamp.java.withdrawal.web.model.CustomerModel;
import com.bootcamp.java.withdrawal.service.ICustomerService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller for Customer Service
 * @author jmacoele
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/Customer")
@Slf4j
public class CustomerController {
	
	@Autowired
	private final ICustomerService customerService;

	/**
	 * Get list of Customers
	 * @return
	 * @throws Exception
	 */
	@GetMapping()	
	@Operation(summary = "Get list of Customers")
	public ResponseEntity<Object> getAll() throws Exception {
		List<CustomerModel> response =  customerService.findAll();
		log.info("getAll" + "OK");
		log.debug(response.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Get Customer by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = { "{id}" }, produces = { "application/json" })
	@Operation(summary = "Get Customer by id")
	public ResponseEntity<CustomerModel> getById(@PathVariable("id") Long id) throws Exception{
		CustomerModel response = customerService.findById(id);
		log.info("getById" + "OK");
		log.debug(id.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Get Customer by DNI
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = { "dni/{dni}" }, produces = { "application/json" })
	@Operation(summary = "Get Customer by DNI")
	public ResponseEntity<Object> getByDni(@PathVariable("dni") String dni) throws Exception{
		List<CustomerModel> response = customerService.findByDni(dni);
		log.info("getByDni" + "OK");
		log.debug(dni.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Create Customer
	 * @param customerModel
	 * @return
	 * @throws Exception
	 */
	@PostMapping()
	@Operation(summary = "Create Customer")
	public ResponseEntity<Object> create(@Valid @RequestBody CustomerModel customerModel) throws Exception {
		CustomerModel response = customerService.create(customerModel);
		log.info("create" + "OK");
		log.debug(customerModel.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Update Customer by id
	 * @param id
	 * @param customerModel
	 * @throws Exception
	 */
	@PutMapping(path = { "{id}" }, produces = { "application/json" })
	@Operation(summary = "Update Customer by id")
	public void update(		
			@PathVariable("id") Long id,
			@Valid @RequestBody CustomerModel customerModel) throws Exception {		
		customerService.update(id, customerModel);
		log.info("update" + "OK");
		log.debug(id.toString()+ "/" + customerModel.toString());
	}
	
	/**
	 * Delete Customer by id
	 * @param id
	 * @throws Exception
	 * @author jmacoele
	 */
	@DeleteMapping({ "{id}" })
	@Operation(summary = "Delete Customer by id")
	public void deleteById(@PathVariable("id") Long id) throws Exception {
		customerService.deleteById(id);
		log.info("deleteById" + "OK");
		log.debug(id.toString());
	}
}
