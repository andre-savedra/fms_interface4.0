package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.Delivery;

public interface FmsDeliveryService {
	public Delivery findDeliveryById(Long id);
	public List<Delivery> findAllDelivery();
	public void saveDelivery(Delivery delivery);
}
