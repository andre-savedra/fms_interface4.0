package com.spring.fms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.fms.model.Order;
import com.spring.fms.model.OrderType;

public interface FmsOrderRepository extends JpaRepository<Order, Long>{
	List<Order> findAllByType(OrderType type);
}
