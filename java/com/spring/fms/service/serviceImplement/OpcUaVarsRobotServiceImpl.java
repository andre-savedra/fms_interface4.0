package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.OpcUaVarsRobot;
import com.spring.fms.repository.OpcUaVarsRobotRepository;
import com.spring.fms.service.OpcUaVarsRobotService;

@Service
public class OpcUaVarsRobotServiceImpl implements OpcUaVarsRobotService {

	@Autowired
	OpcUaVarsRobotRepository varRobotRepository;
	
	@Override
	public List<OpcUaVarsRobot> findRobotVarsAll() {		
		return varRobotRepository.findAll();
	}

	@Override
	public OpcUaVarsRobot findRobotVarById(long id) {		
		return varRobotRepository.findById(id).get();
	}

	@Override
	public OpcUaVarsRobot saveRobotVar(OpcUaVarsRobot var) {		
		return varRobotRepository.save(var);
	}

	@Override
	public OpcUaVarsRobot findRobotLastVar() {
		return varRobotRepository.findLastVar();
	}

}
