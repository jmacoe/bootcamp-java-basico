package com.bootcamp.java.withdrawal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.java.withdrawal.domain.Request;

/**
 * Repository Class for Request Entity
 * @author jmacoele
 *
 */
public interface RequestRepository extends JpaRepository<Request, Long> {


}
