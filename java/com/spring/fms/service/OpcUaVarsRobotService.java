package com.spring.fms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.fms.model.OpcUaVarsRobot;
import com.spring.fms.model.OpcUaVarsTurn;


public interface OpcUaVarsRobotService {
	List<OpcUaVarsRobot> findRobotVarsAll();
	OpcUaVarsRobot findRobotLastVar();
	OpcUaVarsRobot findRobotVarById(long id);
	OpcUaVarsRobot saveRobotVar(OpcUaVarsRobot var);
}
