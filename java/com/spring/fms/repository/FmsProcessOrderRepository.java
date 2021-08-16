package com.spring.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.fms.model.ProcessOrder;

public interface FmsProcessOrderRepository extends JpaRepository<ProcessOrder, Long>{

}
