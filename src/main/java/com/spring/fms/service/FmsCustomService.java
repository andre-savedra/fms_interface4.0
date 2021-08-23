package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.Customization;

public interface FmsCustomService {
	
	List<Customization> findCustomAll();
	Customization findCustomById(long id);
	Customization saveCustom(Customization custom);
}
