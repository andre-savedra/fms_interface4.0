package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.SupervisoryDataExchange;
import com.spring.fms.repository.SupervisoryDataExchangeRepository;
import com.spring.fms.service.SupervisoryDataExchangeService;

@Service
public class SupervisoryDataExchangeServiceImpl implements SupervisoryDataExchangeService {
	
	@Autowired
	SupervisoryDataExchangeRepository supervisoryData;

	@Override
	public List<SupervisoryDataExchange> findDataAll() {	
		return supervisoryData.findAll();
	}

	@Override
	public SupervisoryDataExchange findDataById(long id) {
		return supervisoryData.findById(id).get();
	}

	@Override
	public SupervisoryDataExchange saveData(SupervisoryDataExchange supervisory) {
		return supervisoryData.save(supervisory);
	}

}
