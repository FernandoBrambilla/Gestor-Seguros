package com.fernando.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fernando.Entities.ClientPJ;

public interface ClientPJRepository extends JpaRepository<ClientPJ, Long>{
	
	@Query("SELECT u from ClientPJ u WHERE u.name like ?1%")
	  List<ClientPJ> findByName(@Param("name")String name);

}
