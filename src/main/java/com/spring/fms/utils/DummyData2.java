package com.spring.fms.utils;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.fms.model.OpcUaVarsMilling;
import com.spring.fms.model.OpcUaVarsRobot;
import com.spring.fms.model.OpcUaVarsTurn;
import com.spring.fms.model.SupervisoryDataExchange;
import com.spring.fms.repository.OpcUaVarsMillingRepository;
import com.spring.fms.repository.OpcUaVarsRobotRepository;
import com.spring.fms.repository.OpcUaVarsTurnRepository;
import com.spring.fms.repository.SupervisoryDataExchangeRepository;

@Component
public class DummyData2 {

	@Autowired
	SupervisoryDataExchangeRepository supervisoryRepository;
	
	@Autowired
	OpcUaVarsMillingRepository uaVarMillingRepository;
	
	@Autowired
	OpcUaVarsTurnRepository uaVarTurnRepository;
	
	@Autowired
	OpcUaVarsRobotRepository uaVarRobotRepository;
	

	//@PostConstruct
	public void loadDummy2() {
		SupervisoryDataExchange sd = new SupervisoryDataExchange();
		sd.setId(1L);
		sd.setGcodeMillLoaded(false);
		sd.setGcodeTurnLoaded(false);
		sd.setOrderMillToManufacture(1L);
		sd.setOrderTurnToManufacture(1L);
				
		supervisoryRepository.save(sd);
		
		OpcUaVarsMilling uaMill = new OpcUaVarsMilling();
		uaMill.setPortOpened(false);
		uaMill.setStatusAlarmMill(false);
		uaMill.setMillMachining(true);
		uaMill.setCounterMillPart(3L);
		uaMill.setActualDate(LocalDateTime.now());
		uaVarMillingRepository.save(uaMill);
		
		OpcUaVarsTurn uaTurn = new OpcUaVarsTurn();
		uaTurn.setPortOpened(false);
		uaTurn.setStatusAlarmTurn(false);
		uaTurn.setTurnMachining(false);
		uaTurn.setCounterTurnPart(2L);
		uaTurn.setActualDate(LocalDateTime.now());
		uaVarTurnRepository.save(uaTurn);
		
		OpcUaVarsRobot uaRobot = new OpcUaVarsRobot();
		uaRobot.setStatusRobot(5L);
		uaRobot.setStatusAlarmRobot(false);		
		uaRobot.setActualDate(LocalDateTime.now());
		uaVarTurnRepository.save(uaTurn);

				
		System.out.println("DUMMY DATA2 OK!");


	}
}

