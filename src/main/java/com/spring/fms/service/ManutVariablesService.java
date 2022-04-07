package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.ManutVariables;

public interface ManutVariablesService {

	List<ManutVariables> findManutAll();
	ManutVariables findManutById(long id);
	ManutVariables saveManut(ManutVariables var);
}
