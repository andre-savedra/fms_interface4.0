package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.ProcessOrder;
import com.spring.fms.repository.FmsProcessOrderRepository;
import com.spring.fms.service.FmsProcessOrderService;

@Service
public class FmsProcessOrderServiceImple implements FmsProcessOrderService{

	@Autowired
	FmsProcessOrderRepository processOrderRepository;
	
	@Override
	public List<ProcessOrder> findAllProcessOrder() {		
		return processOrderRepository.findAll();
	}

	@Override
	public ProcessOrder findByIdProcessOrder(long id) {		
		return processOrderRepository.findById(id).get();
	}

	@Override
	public ProcessOrder saveProcessOrder(ProcessOrder process) {		
		return processOrderRepository.save(process);
	}

}
