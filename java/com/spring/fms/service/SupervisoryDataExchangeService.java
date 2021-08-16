package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.SupervisoryDataExchange;

public interface SupervisoryDataExchangeService {
		List<SupervisoryDataExchange> findDataAll();		
		SupervisoryDataExchange findDataById(long id);
		SupervisoryDataExchange saveData(SupervisoryDataExchange supervisory);
		
}
