package com.spring.fms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.fms.model.OrderType;
import com.spring.fms.model.Part;

public interface FmsPartRepository extends JpaRepository<Part, Long>{
	List<Part> findAllByType(OrderType type_id);		
	long count();	
}
