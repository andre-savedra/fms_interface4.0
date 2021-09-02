package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.Magazine;
import com.spring.fms.repository.FmsMagazineRepository;
import com.spring.fms.service.FmsMagazineService;

@Service
public class FmsMagazineServiceImpl implements FmsMagazineService{
	
	@Autowired
	FmsMagazineRepository magazineRepository;

	@Override
	public List<Magazine> findAllMagazine() {		
		return magazineRepository.findAll();
	}

	@Override
	public Magazine findMagazineById(long id) {		
		return magazineRepository.findById(id).get();
	}

	@Override
	public Magazine saveMagazine(Magazine magazine) {		
		return magazineRepository.save(magazine);
	}
	
	
	
	
	
	

}
