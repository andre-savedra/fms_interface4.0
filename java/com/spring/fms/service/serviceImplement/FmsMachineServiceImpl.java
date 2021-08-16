package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.Machine;
import com.spring.fms.repository.FmsMachineRepository;
import com.spring.fms.service.FmsMachineService;

@Service
public class FmsMachineServiceImpl implements FmsMachineService{

	@Autowired
	FmsMachineRepository machineRepository;
	
	@Override
	public List<Machine> findMachineAll() {
		return machineRepository.findAll();
	}

	@Override
	public Machine findMachineById(long id) {		
		return machineRepository.findById(id).get();
	}

	@Override
	public Machine saveMachine(Machine machine) {		
		return machineRepository.save(machine);
	}

	/*@Override
	public List<Part> findAllParts(Machine m) {
		return machineRepository.findAllByPart(m);
	}*/
	

}
