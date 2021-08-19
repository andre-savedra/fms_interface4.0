package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.Order;
import com.spring.fms.model.OrderType;
import com.spring.fms.repository.FmsOrderRepository;
import com.spring.fms.service.FmsOrderService;

@Service
public class FmsOrderServiceImpl implements FmsOrderService {
	
	@Autowired
	FmsOrderRepository orderRepository;
	
	
	@Override
	public List<Order> findOrderAll() {		
		return orderRepository.findAll();
	}

	@Override
	public Order findOrderById(long id) {		
		return orderRepository.findById(id).get();
	}

	@Override
	public Order saveOrder(Order order) {		
		return orderRepository.save(order);
	}

	@Override
	public List<Order> findAllByType(OrderType type) {		
		return orderRepository.findAllByType(type);
	}

	@Override
	public Long count() {
		return orderRepository.count();
	}

	@Override
	public void deleteAllOrder() {
		orderRepository.deleteAll();		
	}

	

}
