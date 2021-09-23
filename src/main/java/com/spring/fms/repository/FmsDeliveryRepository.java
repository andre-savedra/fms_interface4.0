package com.spring.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.fms.model.Delivery;

public interface FmsDeliveryRepository extends JpaRepository<Delivery, Long>{

}
