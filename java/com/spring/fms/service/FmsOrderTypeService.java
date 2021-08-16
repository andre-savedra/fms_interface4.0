package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.OrderType;

public interface FmsOrderTypeService {

	List<OrderType> findOrderTypeAll();
	OrderType findOrderTypeById(long id);
	OrderType saveOrderType(OrderType orderType);
}
