package com.bootcamp.java.withdrawal.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class that stores the data of the withdrawal requests
 * @author jmacoele
 *
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Request {

	/** Primary key
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** Amount to withdraw
	 * 
	 */
	@NotNull
	private Double amount;
	
	/** Withdrawal date
	 * 
	 */
	@NotNull
	private Timestamp retirementDate;
	
	/**
	 * Bank Account Number to deposit funds
	 */
	@NotNull
	@Column(precision = 50)
	private String bankAccountNumber;
}
