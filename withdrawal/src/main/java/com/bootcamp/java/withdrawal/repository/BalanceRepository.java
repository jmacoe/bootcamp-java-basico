package com.bootcamp.java.withdrawal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.java.withdrawal.domain.Balance;

/**
 * Repository Class for Balance Entity
 * @author jmacoele
 *
 */
public interface BalanceRepository extends JpaRepository<Balance, Long> {


}
