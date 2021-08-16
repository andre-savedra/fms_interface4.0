package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.Model;
import com.spring.fms.repository.FmsModelRepository;
import com.spring.fms.service.FmsModelService;

@Service
public class FmsModelServiceImpl implements FmsModelService {

	@Autowired
	FmsModelRepository modelRepository;
	
	@Override
	public List<Model> findModelAll() {		
		return modelRepository.findAll();
	}

	@Override
	public Model findModelById(long id) {		
		return modelRepository.findById(id).get();
	}

	@Override
	public Model saveModel(Model model) {		
		return modelRepository.save(model);
	}

}
