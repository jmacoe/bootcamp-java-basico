package com.bootcamp.java.withdrawal.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.java.withdrawal.web.model.BalanceModel;
import com.bootcamp.java.withdrawal.web.model.DraftModel;
import com.bootcamp.java.withdrawal.web.model.RequestModel;
import com.bootcamp.java.withdrawal.web.model.WithDrawalModel;
import com.bootcamp.java.withdrawal.service.IBalanceService;
import com.bootcamp.java.withdrawal.service.IRequestService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller for Withdrawal methods
 * @author jmacoele
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/Withdrawal")
@Slf4j
public class WithdrawalController {
	
	@Autowired
	private final IRequestService requestService;
	private final IBalanceService balanceService;
	
	/**
	 * Create Draft Request
	 * @param DraftModel
	 * @return BalanceModel
	 * @throws Exception
	 */
	@PostMapping(path = "Draft")
	@Operation(summary = "Draft Balance Request")
	public ResponseEntity<Object> createDraftRequest(@Valid @RequestBody DraftModel draftModel) throws Exception {
		BalanceModel response = balanceService.createDraft(draftModel);
		log.info("create" + "OK");
		log.debug(draftModel.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	/**
	 * Create Withdrawal Request
	 * @param WithDrawalModel
	 * @return RequestModel
	 * @throws Exception
	 */
	@PostMapping()
	@Operation(summary = "Withdrawal Request")
	public ResponseEntity<Object> createWithDrawalRequest(@Valid @RequestBody WithDrawalModel withDrawalModel) throws Exception {
		RequestModel response = requestService.request(withDrawalModel);
		log.info("create" + "OK");
		log.debug(withDrawalModel.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
