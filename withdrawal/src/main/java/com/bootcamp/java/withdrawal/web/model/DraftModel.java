package com.bootcamp.java.withdrawal.web.model;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DraftModel {

	@NotBlank(message="Documento de Identidad del cliente")
	private String dni;
	
	@NotNull(message="Monto a favor del cliente")
	private Double amountAvailable;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Timestamp retirementDate;
	
	@NotBlank(message="Numero de cuenta bancaria del cliente")
	private String bankAccountNumber;
	
}
