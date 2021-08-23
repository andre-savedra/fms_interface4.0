package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.Machine;

public interface FmsMachineService {
	
	List<Machine> findMachineAll();
	Machine findMachineById(long id);
	Machine saveMachine(Machine machine);
	//List<Part> findAllParts(Machine m);
}
