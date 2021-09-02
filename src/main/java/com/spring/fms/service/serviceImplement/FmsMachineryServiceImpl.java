package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.Machinery;
import com.spring.fms.repository.FmsMachineryRepository;
import com.spring.fms.service.FmsMachineryService;

@Service
public class FmsMachineryServiceImpl implements FmsMachineryService  {
	
	@Autowired
	FmsMachineryRepository machineryRepository;

	@Override
	public Machinery findMachineryById(Long id) {
		return machineryRepository.findById(id).get();
	}

	@Override
	public List<Machinery> findMachineryAll() {		
		return machineryRepository.findAll();
	}

	@Override
	public Machinery saveMachinery(Machinery machinery) {		
		return machineryRepository.save(machinery);
	}

}
