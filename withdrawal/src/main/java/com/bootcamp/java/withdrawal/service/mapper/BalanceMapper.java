package com.bootcamp.java.withdrawal.service.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.bootcamp.java.withdrawal.domain.Balance;
import com.bootcamp.java.withdrawal.web.model.BalanceModel;

@Mapper(componentModel = "spring")
public interface BalanceMapper {

	 Balance balanceModelToBalance (BalanceModel model);
	 
	 BalanceModel balanceToBalanceModel (Balance balance);
	 
	 List<BalanceModel> balancesToBalanceModels(List<Balance> balances);
	
	 
	 @Mapping(target = "id", ignore = true)
	 void update(@MappingTarget Balance entity, BalanceModel updateEntity);
}
