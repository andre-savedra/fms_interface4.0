package com.spring.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.fms.model.OpcUaVarsMilling;

public interface OpcUaVarsMillingRepository extends JpaRepository<OpcUaVarsMilling, Long>{
	@Query(value = "SELECT *from tb_milling_variables where id = (select max(id) from tb_milling_variables)", nativeQuery = true)
	public OpcUaVarsMilling findLastVar();
}
