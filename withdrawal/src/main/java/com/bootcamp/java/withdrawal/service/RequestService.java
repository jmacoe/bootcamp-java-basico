package com.bootcamp.java.withdrawal.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bootcamp.java.withdrawal.domain.Customer;
import com.bootcamp.java.withdrawal.domain.Request;
import com.bootcamp.java.withdrawal.service.mapper.RequestMapper;
import com.bootcamp.java.withdrawal.web.model.RequestModel;
import com.bootcamp.java.withdrawal.web.model.WithDrawalModel;
import com.bootcamp.java.withdrawal.repository.CustomerRepository;
import com.bootcamp.java.withdrawal.repository.RequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestService implements IRequestService {

	private final CustomerRepository customerRepository;
	private final RequestRepository requestRepository;
	private final RequestMapper requestMapper;

	
	@Override
	public List<RequestModel> findAll()  throws Exception {
		List<Request> requests = requestRepository.findAll();
		return requestMapper.requestsToRequestModels(requests);
	}
	
	@Override
	public RequestModel findById(Long id) throws Exception {
		Optional<Request> request = requestRepository.findById(id);
		if(request.isPresent())	return requestMapper.requestToRequestModel(request.get());
		else throw new Exception("No se encontraron datos");
	}

	@Override
	public RequestModel create(RequestModel requestModel)  throws Exception{
		Request request = requestRepository.save(requestMapper.requestModelToRequest(requestModel));
		return requestMapper.requestToRequestModel(request);
	}

	@Override
	public void update(Long id, RequestModel RequestModel)  throws Exception {
		Optional<Request> requestOptional = requestRepository.findById(id);
		
		if(requestOptional.isPresent()) {
			Request requestToUpdate = requestOptional.get();
			requestMapper.update(requestToUpdate, RequestModel);
			requestRepository.save(requestToUpdate);
		}
		else throw new Exception("No se encontraron datos");
	}

	@Override
	public void deleteById(Long id)  throws Exception {
		requestRepository.deleteById(id);
	}
	
	@Override
	public RequestModel request(WithDrawalModel withDrawalModel)  throws Exception{
		
		List<Customer> customers = customerRepository
				.findByDni(withDrawalModel.getDni());
		
		if (customers.isEmpty()) throw new Exception("No se encontro al cliente.");
		
		Customer customer = customers.get(0);

		if (customer.getRequest() != null) throw new Exception("El cliente ya tiene una solicitud registrada.");
		
		if (customer.getBalance() == null) throw new Exception("El cliente aun no esta autorizado para solicitar un retiro.");
		
		if (!customer.getAfp().equalsIgnoreCase(withDrawalModel.getAfp())) throw new Exception("Cliente no esta asociado a la AFP de la solicitud.");

		if (customer.getBalance().getAmountAvailable() < withDrawalModel.getAmount()) throw new Exception("No se puede registrar la solicitud. Monto mayor que el permitido.");
		
		if ((customer.getBalance().getAmountAvailable() / 2) > withDrawalModel.getAmount()) throw new Exception("Monto mínimo no cubierto por favor revise el monto mínimo a retirar.");
		
		Request request = new Request();

		request.setAmount(withDrawalModel.getAmount());
		request.setBankAccountNumber(customer.getBalance().getBankAccountNumber());
		request.setRetirementDate(customer.getBalance().getRetirementDate());
		
		requestRepository.save(request);
		
		customer.setRequest(request);
		customerRepository.save(customer);
		
		return requestMapper.requestToRequestModel(request);
	}

}
