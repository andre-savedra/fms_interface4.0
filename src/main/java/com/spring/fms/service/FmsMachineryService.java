package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.Machinery;

public interface FmsMachineryService {

	public Machinery findMachineryById(Long id);
	public List<Machinery> findMachineryAll();
	public Machinery saveMachinery(Machinery machinery);
	
}
