package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.OpcUaVarsMilling;

public interface OpcUaVarsMillingService {

	List<OpcUaVarsMilling> findMillingVarsAll();
	OpcUaVarsMilling findMillingLastVar();
	OpcUaVarsMilling findMillingVarById(long id);
	OpcUaVarsMilling saveMillingVar(OpcUaVarsMilling var);
}
