package com.bootcamp.java.withdrawal.web.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author jmacoele
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WithDrawalModel {

	@NotBlank(message="Documento de Identidad del cliente")
	private String dni;
	
	@NotNull(message="Monto a retirar")
	private Double amount;
	
	@NotBlank(message="Codigo de la AFP")
	private String afp;
	
}
