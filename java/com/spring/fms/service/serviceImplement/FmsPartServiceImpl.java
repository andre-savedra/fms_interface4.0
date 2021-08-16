package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.OrderType;
import com.spring.fms.model.Part;
import com.spring.fms.repository.FmsPartRepository;
import com.spring.fms.service.FmsPartService;

@Service
public class FmsPartServiceImpl implements FmsPartService{
	
	@Autowired
	FmsPartRepository partRepository;

	@Override
	public List<Part> findPartAll() {		
		return partRepository.findAll();
	}

	@Override
	public Part findPartById(long id) {		
		return partRepository.findById(id).get();
	}

	@Override
	public Part savePart(Part part) {		
		return partRepository.save(part);
	}

	
	@Override
	public Long count() {
		return partRepository.count();
	}

	@Override
	public List<Part> findAllByType(OrderType type_id) {		
		return partRepository.findAllByType(type_id);
	}

	
	

}
