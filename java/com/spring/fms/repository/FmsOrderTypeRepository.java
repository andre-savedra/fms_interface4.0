package com.spring.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.fms.model.OrderType;

public interface FmsOrderTypeRepository extends JpaRepository<OrderType, Long>{
	
}
