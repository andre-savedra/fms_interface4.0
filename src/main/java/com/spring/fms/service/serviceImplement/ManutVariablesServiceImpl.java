package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.ManutVariables;
import com.spring.fms.repository.ManutVariablesRepository;
import com.spring.fms.service.ManutVariablesService;

@Service
public class ManutVariablesServiceImpl implements ManutVariablesService {
	
	@Autowired
	ManutVariablesRepository manutVariablesRepository;

	@Override
	public List<ManutVariables> findManutAll() {
		return manutVariablesRepository.findAll();
	}

	@Override
	public ManutVariables findManutById(long id) {
		return manutVariablesRepository.findById(id).get();
	}

	@Override
	public ManutVariables saveManut(ManutVariables var) {
		return manutVariablesRepository.save(var);
	}

	
}
