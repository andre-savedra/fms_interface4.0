package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.StepOrder;
import com.spring.fms.repository.FmsStepOrderRepository;
import com.spring.fms.service.FmsStepOrderService;

@Service
public class FmsStepOrderServiceImpl implements FmsStepOrderService{

	@Autowired
	FmsStepOrderRepository stepOrderRepository;
	
	@Override
	public List<StepOrder> findAllStepOrder() {
		return stepOrderRepository.findAll();
	}

	@Override
	public StepOrder findByIdStepOrder(long id) {
		return stepOrderRepository.findById(id).get();
	}

	@Override
	public StepOrder saveStepOrder(StepOrder step) {		
		return stepOrderRepository.save(step);
	}

}
