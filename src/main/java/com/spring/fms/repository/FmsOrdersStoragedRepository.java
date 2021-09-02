package com.spring.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.fms.model.OrdersStoraged;

public interface FmsOrdersStoragedRepository extends JpaRepository<OrdersStoraged, Long> {

}
