package com.fernando.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernando.Entities.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
	
}
