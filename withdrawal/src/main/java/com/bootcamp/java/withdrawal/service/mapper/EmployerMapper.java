package com.bootcamp.java.withdrawal.service.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.bootcamp.java.withdrawal.domain.Employer;
import com.bootcamp.java.withdrawal.web.model.EmployerModel;

@Mapper(componentModel = "spring")
public interface EmployerMapper {

	 Employer employerModelToEmployer (EmployerModel model);
	 
	 EmployerModel employerToEmployerModel (Employer employer);
	 
	 List<EmployerModel> employersToEmployerModels(List<Employer> employers);
	
	 
	 @Mapping(target = "id", ignore = true)
	 void update(@MappingTarget Employer entity, EmployerModel updateEntity);
}
