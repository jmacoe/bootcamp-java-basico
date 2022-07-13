package com.bootcamp.java.withdrawal.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bootcamp.java.withdrawal.domain.Balance;
import com.bootcamp.java.withdrawal.domain.Customer;
import com.bootcamp.java.withdrawal.service.mapper.BalanceMapper;
import com.bootcamp.java.withdrawal.web.model.BalanceModel;
import com.bootcamp.java.withdrawal.web.model.DraftModel;
import com.bootcamp.java.withdrawal.repository.BalanceRepository;
import com.bootcamp.java.withdrawal.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BalanceService implements IBalanceService {

	private final CustomerRepository customerRepository;
	private final BalanceRepository balanceRepository;
	private final BalanceMapper balanceMapper;

	
	@Override
	public List<BalanceModel> findAll()  throws Exception {
		List<Balance> balances = balanceRepository.findAll();
		return balanceMapper.balancesToBalanceModels(balances);
	}
	
	@Override
	public BalanceModel findById(Long id) throws Exception {
		Optional<Balance> balance = balanceRepository.findById(id);
		if(balance.isPresent())	return balanceMapper.balanceToBalanceModel(balance.get());
		else throw new Exception("No se encontraron datos");
	}

	@Override
	public BalanceModel create(BalanceModel balanceModel)  throws Exception{
		Balance balance = balanceRepository.save(balanceMapper.balanceModelToBalance(balanceModel));
		return balanceMapper.balanceToBalanceModel(balance);
	}
	
	@Override
	public BalanceModel createDraft(DraftModel draftModel) throws Exception {
		
		if (draftModel.getAmountAvailable() < 0) throw new Exception("El saldo minimo es 0.");
		
		List<Customer> customers = customerRepository
				.findByDni(draftModel.getDni());
		
		if (customers.isEmpty()) throw new Exception("No se encontro al cliente.");
		
		Customer customer = customers.get(0);

		if (customer.getBalance() != null) throw new Exception("El cliente ya esta autorizado para solicitar un retiro.");
			
		Balance balance = new Balance();

		balance.setAmountAvailable(draftModel.getAmountAvailable());
		balance.setBankAccountNumber(draftModel.getBankAccountNumber());
		balance.setRetirementDate(draftModel.getRetirementDate());
		
		balanceRepository.saveAndFlush(balance);
		
		customer.setBalance(balance);
		customerRepository.saveAndFlush(customer);
		
		return balanceMapper.balanceToBalanceModel(balance);
	}

	@Override
	public void update(Long id, BalanceModel balanceModel)  throws Exception {
		Optional<Balance> balanceOptional = balanceRepository.findById(id);
		
		if(balanceOptional.isPresent()) {
			Balance balanceToUpdate = balanceOptional.get();
			balanceMapper.update(balanceToUpdate, balanceModel);
			balanceRepository.save(balanceToUpdate);
		}
		else throw new Exception("No se encontraron datos");
	}

	@Override
	public void deleteById(Long id)  throws Exception {
		balanceRepository.deleteById(id);
	}

}
