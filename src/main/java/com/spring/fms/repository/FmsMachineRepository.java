package com.spring.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.fms.model.Machine;

public interface FmsMachineRepository extends JpaRepository<Machine, Long>{
	//List<Part> findAllByPart(Machine m);
}


