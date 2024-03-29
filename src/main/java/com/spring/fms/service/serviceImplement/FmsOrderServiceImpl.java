package com.spring.fms.service.serviceImplement;

import java.util.List;
import java.util.Optional;

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
	public Optional<Order> findOrderById(long id) {
		return orderRepository.findById(id);
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

	@Override
	public void deleteOrderById(Order order) { orderRepository.delete(order); }

	@Override
	public List<Order> findOrderAllToProduce() {		
		return orderRepository.findOrdersToProduce();
	}

	@Override
	public List<Order> findOrderAllManufacturing() {		
		return orderRepository.findOrdersManufacturing();
	}

	

}
