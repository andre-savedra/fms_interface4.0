package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.OpcUaVarsTurn;
import com.spring.fms.repository.OpcUaVarsTurnRepository;
import com.spring.fms.service.OpcUaVarsTurnService;

@Service
public class OpcUaVarsTurnServiceImpl implements OpcUaVarsTurnService{

	@Autowired
	OpcUaVarsTurnRepository varTurnRepository;
	
	
	@Override
	public List<OpcUaVarsTurn> findTurnVarsAll() {		
		return varTurnRepository.findAll();
	}

	@Override
	public OpcUaVarsTurn findTurnVarById(long id) {		
		return varTurnRepository.findById(id).get();
	}

	@Override
	public OpcUaVarsTurn saveTurnVar(OpcUaVarsTurn var) {		
		return varTurnRepository.save(var);
	}

	@Override
	public OpcUaVarsTurn findTurnLastVar() {		
		return varTurnRepository.findLastVar();
	}

}
