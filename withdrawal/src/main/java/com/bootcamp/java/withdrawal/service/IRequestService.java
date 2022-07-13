package com.bootcamp.java.withdrawal.service;

import java.util.List;

import com.bootcamp.java.withdrawal.web.model.RequestModel;
import com.bootcamp.java.withdrawal.web.model.WithDrawalModel;

public interface IRequestService {
	List<RequestModel> findAll() throws Exception;
	
	RequestModel findById(Long id) throws Exception;
	
	RequestModel create(RequestModel requestModel) throws Exception;

	void update(Long id, RequestModel requestModel) throws Exception;

	void deleteById(Long id) throws Exception;

	RequestModel request(WithDrawalModel withDrawalModel) throws Exception;
}
