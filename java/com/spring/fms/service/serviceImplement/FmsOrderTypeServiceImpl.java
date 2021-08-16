package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.OrderType;
import com.spring.fms.repository.FmsOrderTypeRepository;
import com.spring.fms.service.FmsOrderTypeService;

@Service
public class FmsOrderTypeServiceImpl implements FmsOrderTypeService{

	@Autowired
	FmsOrderTypeRepository orderTypeRepository;
	
	@Override
	public List<OrderType> findOrderTypeAll() {		
		return orderTypeRepository.findAll();
	}

	@Override
	public OrderType findOrderTypeById(long id) {		
		return orderTypeRepository.findById(id).get();
	}

	@Override
	public OrderType saveOrderType(OrderType orderType) {		
		return orderTypeRepository.save(orderType);
	}

}
