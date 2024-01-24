package com.fernando.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernando.Entities.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {
	
}
