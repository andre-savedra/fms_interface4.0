package com.spring.fms.service;

import java.util.List;
import java.util.Optional;

import com.spring.fms.model.Order;
import com.spring.fms.model.OrderType;

public interface FmsOrderService {
	List<Order> findOrderAllToProduce();
	List<Order> findOrderAllManufacturing();
	List<Order> findAllByType(OrderType type);
	List<Order> findOrderAll();	
	Optional<Order> findOrderById(long id);
	Order saveOrder(Order order);
	Long count();
	void deleteAllOrder();
	void deleteOrderById(Order order);
}
