package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.ProcessPart;

public interface FmsProcessPartService {
	List<ProcessPart> findAllProcessPart();
	ProcessPart findByIdProcessPart(long id);
	ProcessPart saveProcessPart(ProcessPart process);	
}
