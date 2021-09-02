package com.spring.fms.utils;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.fms.model.Magazine;
import com.spring.fms.model.OpcUaVarsMilling;
import com.spring.fms.model.OpcUaVarsRobot;
import com.spring.fms.model.OpcUaVarsTurn;
import com.spring.fms.model.SupervisoryDataExchange;
import com.spring.fms.repository.FmsMagazineRepository;
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
	
	@Autowired
	FmsMagazineRepository magazineRepository_;	

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

				
		Magazine mg1 = new Magazine();
		mg1.setLocated(false);
		mg1.setName("Magazine1");
		magazineRepository_.save(mg1);
		
		Magazine mg2 = new Magazine();
		mg2.setLocated(false);
		mg2.setName("Magazine2");
		magazineRepository_.save(mg2);
		
		Magazine mg3 = new Magazine();
		mg3.setLocated(false);
		mg3.setName("Magazine3");
		magazineRepository_.save(mg3);
		
		Magazine mg4 = new Magazine();
		mg4.setLocated(false);
		mg4.setName("Magazine4");
		magazineRepository_.save(mg4);
		
		Magazine mg5 = new Magazine();
		mg5.setLocated(false);
		mg5.setName("Magazine5");
		magazineRepository_.save(mg5);
		
		Magazine mg6 = new Magazine();
		mg6.setLocated(false);
		mg6.setName("Magazine6");
		magazineRepository_.save(mg6);
		
		
		System.out.println("DUMMY DATA2 OK!");


	}
}

