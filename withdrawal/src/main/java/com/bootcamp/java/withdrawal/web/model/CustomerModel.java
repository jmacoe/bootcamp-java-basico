package com.bootcamp.java.withdrawal.web.model;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {
	
	@JsonProperty("customerId")
	private Long id;
	
	@NotBlank(message="Numero de DNI")
	private String dni;
	
	@NotBlank(message="Nombres del cliente")
	private String firstname;
	
	@NotBlank(message="Apellidos del cliente")
	private String lastname;
	
	@NotBlank(message="Numero Fijo o Celular del cliente")
	private String phoneNumber;
	
	@NotBlank(message="Correo electronico del cliente")
	private String email;
	
	@NotBlank(message="Codigo de la AFP")
	private String afp;
	
	private BalanceModel balance;
	
	private RequestModel request;
	
	private Set<EmployerModel> employers;
	
}
