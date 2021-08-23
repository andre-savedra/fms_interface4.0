package com.spring.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.fms.model.OpcUaVarsTurn;

public interface OpcUaVarsTurnRepository extends JpaRepository<OpcUaVarsTurn, Long>{
	@Query(value = "SELECT *from tb_turn_variables where id = (select max(id) from tb_turn_variables)", nativeQuery = true)
	public OpcUaVarsTurn findLastVar();
}
