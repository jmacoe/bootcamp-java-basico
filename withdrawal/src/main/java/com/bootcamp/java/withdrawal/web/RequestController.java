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

import com.bootcamp.java.withdrawal.web.model.RequestModel;
import com.bootcamp.java.withdrawal.service.IRequestService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller for Request Service
 * @author jmacoele
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/Request")
@Slf4j
public class RequestController {
	
	@Autowired
	private final IRequestService requestService;

	/**
	 * Get list of Requests
	 * @return
	 * @throws Exception
	 */
	@GetMapping()	
	@Operation(summary = "Get list of Requests")
	public ResponseEntity<Object> getAll() throws Exception {
		List<RequestModel> response =  requestService.findAll();
		log.info("getAll" + "OK");
		log.debug(response.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Get Request by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = { "{id}" }, produces = { "application/json" })
	@Operation(summary = "Get Request by id")
	public ResponseEntity<RequestModel> getById(@PathVariable("id") Long id) throws Exception{
		RequestModel response = requestService.findById(id);
		log.info("getById" + "OK");
		log.debug(id.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Create Request
	 * @param requestModel
	 * @return
	 * @throws Exception
	 */
	@PostMapping()
	@Operation(summary = "Create Request")
	public ResponseEntity<Object> create(@Valid @RequestBody RequestModel requestModel) throws Exception {
		RequestModel response = requestService.create(requestModel);
		log.info("create" + "OK");
		log.debug(requestModel.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Update Request by id
	 * @param id
	 * @param requestModel
	 * @throws Exception
	 */
	@PutMapping(path = { "{id}" }, produces = { "application/json" })
	@Operation(summary = "Update Request by id")
	public void update(		
			@PathVariable("id") Long id,
			@Valid @RequestBody RequestModel requestModel) throws Exception {		
		requestService.update(id, requestModel);
		log.info("update" + "OK");
		log.debug(id.toString()+ "/" + requestModel.toString());
	}
	
	/**
	 * Delete Request by id
	 * @param id
	 * @throws Exception
	 * @author jmacoele
	 */
	@DeleteMapping({ "{id}" })
	@Operation(summary = "Delete Request by id")
	public void deleteById(@PathVariable("id") Long id) throws Exception {
		requestService.deleteById(id);
		log.info("deleteById" + "OK");
		log.debug(id.toString());
	}
}
