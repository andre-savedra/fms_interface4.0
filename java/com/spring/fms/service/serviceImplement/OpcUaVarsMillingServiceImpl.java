package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.OpcUaVarsMilling;
import com.spring.fms.repository.OpcUaVarsMillingRepository;
import com.spring.fms.service.OpcUaVarsMillingService;

@Service
public class OpcUaVarsMillingServiceImpl implements OpcUaVarsMillingService {
	
	@Autowired
	OpcUaVarsMillingRepository varMillingRepository;

	@Override
	public List<OpcUaVarsMilling> findMillingVarsAll() {		
		return varMillingRepository.findAll();
	}

	@Override
	public OpcUaVarsMilling findMillingVarById(long id) {
		return varMillingRepository.findById(id).get();
	}

	@Override
	public OpcUaVarsMilling saveMillingVar(OpcUaVarsMilling var) {		
		return varMillingRepository.save(var);
	}

	@Override
	public OpcUaVarsMilling findMillingLastVar() {
		return varMillingRepository.findLastVar();
	}

}
