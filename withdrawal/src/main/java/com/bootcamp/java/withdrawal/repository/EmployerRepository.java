package com.bootcamp.java.withdrawal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.java.withdrawal.domain.Employer;

/**
 * Repository Class for Employer Entity
 * @author jmacoele
 *
 */
public interface EmployerRepository extends JpaRepository<Employer, Long> {


}
