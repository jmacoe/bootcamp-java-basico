package com.bootcamp.java.withdrawal.web.model;

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
public class EmployerModel {

	@JsonProperty("employerId")
	private Long id;
	
	@NotBlank(message="Numero de RUC de la empresa")
	private String ruc;
	
	@NotBlank(message="Razón Social de la empresa")
	private String companyName;
	
}
