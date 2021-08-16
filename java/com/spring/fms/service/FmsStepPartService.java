package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.StepPart;

public interface FmsStepPartService {
	List<StepPart> findAllStepPart();
	StepPart findByIdStepPart(long id);
	StepPart saveStepPart(StepPart step);
}
