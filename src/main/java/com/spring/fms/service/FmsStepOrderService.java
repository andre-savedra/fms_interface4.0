package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.StepOrder;

public interface FmsStepOrderService {
	List<StepOrder> findAllStepOrder();
	StepOrder findByIdStepOrder(long id);
	StepOrder saveStepOrder(StepOrder step);	
}
