package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.Customization;
import com.spring.fms.repository.FmsCustomRepository;
import com.spring.fms.service.FmsCustomService;

@Service
public class FmsCustomServiceImpl implements FmsCustomService{
	
	@Autowired
	FmsCustomRepository customRepository;

	@Override
	public List<Customization> findCustomAll() {		
		return customRepository.findAll();
	}

	@Override
	public Customization findCustomById(long id) {		
		return customRepository.findById(id).get();
	}

	@Override
	public Customization saveCustom(Customization custom) {		
		return customRepository.save(custom);
	}

}
