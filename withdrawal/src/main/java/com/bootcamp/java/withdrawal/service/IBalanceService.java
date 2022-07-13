package com.bootcamp.java.withdrawal.service;

import java.util.List;

import com.bootcamp.java.withdrawal.web.model.BalanceModel;
import com.bootcamp.java.withdrawal.web.model.DraftModel;

public interface IBalanceService {
	List<BalanceModel> findAll() throws Exception;
	
	BalanceModel findById(Long id) throws Exception;

	BalanceModel create(BalanceModel balanceModel) throws Exception;
	
	BalanceModel createDraft(DraftModel draftModel) throws Exception;

	void update(Long id, BalanceModel balanceModel) throws Exception;

	void deleteById(Long id) throws Exception;
	
}
