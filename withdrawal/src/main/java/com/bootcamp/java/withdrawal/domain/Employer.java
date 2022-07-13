package com.bootcamp.java.withdrawal.domain;

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
 * Entity class that stores employer data
 * @author jmacoele
 *
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employer {

	/** Primary Key
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** Unique taxpayer registration number
	 * 
	 */
	@NotNull
	@Column(unique=true)
	private String ruc;
	
	
	/**
	 * Company's Name
	 */
	@NotNull
	private String companyName;
	
}
