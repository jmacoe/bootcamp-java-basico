package com.bootcamp.java.withdrawal.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class that stores the customer data associated with the AFPs
 * @author jmacoele
 *
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	/** Primary Key
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** Identification Document Number
	 * 
	 */
	@NotNull
	@Column(unique=true)
	private String dni;
	
	/** Client's First Name
	 * 
	 */
	@NotNull
	@Column(precision = 50)
	private String firstname;
	
	/** Client's Last Name
	 * 
	 */
	@NotNull
	@Column(precision = 50)
	private String lastname;
	
	/** Client's Phone Number
	 * 
	 */
	@Column(precision = 15)
	private String phoneNumber; 
	
	/** Client's Email
	 * 
	 */
	@Column(precision = 100)
	private String email;
	
	/** AFP Name associate to client
	 * 
	 */
	@NotNull
	@Column(precision = 10)
	@Pattern(regexp="(PRIMA|INTEGRA|PROFUTURO|HABITAT)")
	private String afp;
	
	/** Limits of Withdrawal request
	 * 
	 */
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Balance balance;
	
	/** Client's Employeers
	 * 
	 */
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Set<Employer> employer;
	
	/** Client's Withdrawal request
	 * 
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Request request;
}
