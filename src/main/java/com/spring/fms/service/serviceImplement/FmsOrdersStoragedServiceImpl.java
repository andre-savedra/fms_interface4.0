package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.OrdersStoraged;
import com.spring.fms.repository.FmsOrdersStoragedRepository;
import com.spring.fms.service.FmsOrdersStoragedService;

@Service
public class FmsOrdersStoragedServiceImpl implements FmsOrdersStoragedService {
	
	@Autowired
	FmsOrdersStoragedRepository ordersStoragedRepository;

	@Override
	public List<OrdersStoraged> findAllOrdersStoraged() {		
		return ordersStoragedRepository.findAll();
	}

	@Override
	public OrdersStoraged findOrdersStoragedById(long id) {		
		return ordersStoragedRepository.findById(id).get();
	}

	@Override
	public OrdersStoraged saveOrdersStoraged(OrdersStoraged order) {
		return ordersStoragedRepository.save(order);
	}

	
	

}
