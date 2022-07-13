package com.bootcamp.java.withdrawal.service.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.bootcamp.java.withdrawal.domain.Request;
import com.bootcamp.java.withdrawal.web.model.RequestModel;

@Mapper(componentModel = "spring")
public interface RequestMapper {

	 Request requestModelToRequest (RequestModel model);
	 
	 RequestModel requestToRequestModel (Request request);
	 
	 List<RequestModel> requestsToRequestModels(List<Request> requests);
	
	 
	 @Mapping(target = "id", ignore = true)
	 void update(@MappingTarget Request entity, RequestModel updateEntity);
}
