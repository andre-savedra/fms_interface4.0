package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.OrderType;
import com.spring.fms.model.Part;

public interface FmsPartService {
	
	List<Part> findAllByType(OrderType type_id);
	List<Part> findPartAll();
	Part findPartById(long id);
	Part savePart(Part part);	
	Long count();
}
