package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.OpcUaVarsRobot;


public interface OpcUaVarsRobotService {
	List<OpcUaVarsRobot> findRobotVarsAll();
	OpcUaVarsRobot findRobotLastVar();
	OpcUaVarsRobot findRobotVarById(long id);
	OpcUaVarsRobot saveRobotVar(OpcUaVarsRobot var);
}
