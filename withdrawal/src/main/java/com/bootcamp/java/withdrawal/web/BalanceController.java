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

import com.bootcamp.java.withdrawal.web.model.BalanceModel;
import com.bootcamp.java.withdrawal.service.IBalanceService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller for Balance Service
 * @author jmacoele
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/Balance")
@Slf4j
public class BalanceController {
	
	@Autowired
	private final IBalanceService balanceService;

	/**
	 * Get list of Balances
	 * @return
	 * @throws Exception
	 */
	@GetMapping()	
	@Operation(summary = "Get list of Balances")
	public ResponseEntity<Object> getAll() throws Exception {
		List<BalanceModel> response =  balanceService.findAll();
		log.info("getAll" + "OK");
		log.debug(response.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Get Balance by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = { "{id}" }, produces = { "application/json" })
	@Operation(summary = "Get Balance by id")
	public ResponseEntity<BalanceModel> getById(@PathVariable("id") Long id) throws Exception{
		BalanceModel response = balanceService.findById(id);
		log.info("getById" + "OK");
		log.debug(id.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Create Balance
	 * @param balanceModel
	 * @return
	 * @throws Exception
	 */
	@PostMapping()
	@Operation(summary = "Create Balance")
	public ResponseEntity<Object> create(@Valid @RequestBody BalanceModel balanceModel) throws Exception {
		BalanceModel response = balanceService.create(balanceModel);
		log.info("create" + "OK");
		log.debug(balanceModel.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Update Balance by id
	 * @param id
	 * @param balanceModel
	 * @throws Exception
	 */
	@PutMapping(path = { "{id}" }, produces = { "application/json" })
	@Operation(summary = "Update Balance by id")
	public void update(		
			@PathVariable("id") Long id,
			@Valid @RequestBody BalanceModel balanceModel) throws Exception {		
		balanceService.update(id, balanceModel);
		log.info("update" + "OK");
		log.debug(id.toString()+ "/" + balanceModel.toString());
	}
	
	/**
	 * Delete Balance by id
	 * @param id
	 * @throws Exception
	 * @author jmacoele
	 */
	@DeleteMapping({ "{id}" })
	@Operation(summary = "Delete Balance by id")
	public void deleteById(@PathVariable("id") Long id) throws Exception {
		balanceService.deleteById(id);
		log.info("deleteById" + "OK");
		log.debug(id.toString());
	}
}
