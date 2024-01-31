package com.fernando.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fernando.Entities.ClientPF;


public interface ClientPFRepository extends JpaRepository<ClientPF, Long> {
	
	
	 @Query("SELECT u from ClientPF u WHERE u.name like ?1%")
	  List<ClientPF> findByName(@Param("name")String name);
	}
