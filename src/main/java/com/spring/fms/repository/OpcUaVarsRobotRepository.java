package com.spring.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.fms.model.OpcUaVarsRobot;

public interface OpcUaVarsRobotRepository extends JpaRepository<OpcUaVarsRobot, Long>{
	@Query(value = "SELECT *from tb_robot_variables where id = (select max(id) from tb_robot_variables)", nativeQuery = true)
	public OpcUaVarsRobot findLastVar();
	
}
