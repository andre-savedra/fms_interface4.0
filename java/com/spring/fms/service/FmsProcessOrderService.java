package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.ProcessOrder;

public interface FmsProcessOrderService {
	List<ProcessOrder> findAllProcessOrder();
	ProcessOrder findByIdProcessOrder(long id);
	ProcessOrder saveProcessOrder(ProcessOrder process);	
}
