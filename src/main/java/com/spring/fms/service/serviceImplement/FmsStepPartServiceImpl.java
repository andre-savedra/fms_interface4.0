package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.StepPart;
import com.spring.fms.repository.FmsStepPartRepository;
import com.spring.fms.service.FmsStepPartService;

@Service
public class FmsStepPartServiceImpl implements FmsStepPartService{

	@Autowired
	FmsStepPartRepository stepPartRepository;
	
	@Override
	public List<StepPart> findAllStepPart() {		
		return stepPartRepository.findAll();
	}

	@Override
	public StepPart findByIdStepPart(long id) {		
		return stepPartRepository.findById(id).get();
	}

	@Override
	public StepPart saveStepPart(StepPart step) {		
		return stepPartRepository.save(step);
	}

	
}
