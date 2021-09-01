package com.spring.fms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.fms.model.OpcUaVarsMilling;
import com.spring.fms.model.Order;
import com.spring.fms.model.OrderType;

public interface FmsOrderRepository extends JpaRepository<Order, Long>{
	@Query(value = "SELECT *from tb_order where produced = 0", nativeQuery = true)
	List<Order> findOrdersToProduce();
	
	List<Order> findAllByType(OrderType type);
	long count();
}
