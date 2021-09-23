package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.Delivery;
import com.spring.fms.repository.FmsDeliveryRepository;
import com.spring.fms.service.FmsDeliveryService;

@Service
public class FmsDeliveryServiceImpl implements FmsDeliveryService {

	@Autowired
	FmsDeliveryRepository deliveryRepository;
	
	@Override
	public Delivery findDeliveryById(Long id) {		
		return deliveryRepository.findById(id).get();
	}

	@Override
	public List<Delivery> findAllDelivery() {		
		return deliveryRepository.findAll();
	}

	@Override
	public void saveDelivery(Delivery delivery) {
		deliveryRepository.save(delivery);
	}

}
