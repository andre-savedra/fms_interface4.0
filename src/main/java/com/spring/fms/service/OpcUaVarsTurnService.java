package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.OpcUaVarsTurn;

public interface OpcUaVarsTurnService {

	List<OpcUaVarsTurn> findTurnVarsAll();
	OpcUaVarsTurn findTurnLastVar();
	OpcUaVarsTurn findTurnVarById(long id);
	OpcUaVarsTurn saveTurnVar(OpcUaVarsTurn var);
	
}
