package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.OrdersStoraged;

public interface FmsOrdersStoragedService {
	List<OrdersStoraged> findAllOrdersStoraged();
	OrdersStoraged findOrdersStoragedById(long id);
	OrdersStoraged saveOrdersStoraged(OrdersStoraged order);	
}
