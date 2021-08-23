package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.ProcessPart;
import com.spring.fms.repository.FmsProcessPartRepository;
import com.spring.fms.service.FmsProcessPartService;

@Service
public class FmsProcessPartServiceImpl implements FmsProcessPartService{

	@Autowired
	FmsProcessPartRepository processPartRepository;
	
	@Override
	public List<ProcessPart> findAllProcessPart() {		
		return processPartRepository.findAll();
	}

	@Override
	public ProcessPart findByIdProcessPart(long id) {		
		return processPartRepository.findById(id).get();
	}

	@Override
	public ProcessPart saveProcessPart(ProcessPart process) {		
		return processPartRepository.save(process);
	}

}
