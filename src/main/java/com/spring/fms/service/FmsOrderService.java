package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.Order;
import com.spring.fms.model.OrderType;

public interface FmsOrderService {

	List<Order> findAllByType(OrderType type);
	List<Order> findOrderAll();
	Order findOrderById(long id);
	Order saveOrder(Order order);
	Long count();
	void deleteAllOrder();
	
}
