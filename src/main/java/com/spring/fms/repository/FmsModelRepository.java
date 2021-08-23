package com.spring.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.fms.model.Model;

public interface FmsModelRepository extends JpaRepository<Model, Long> {

}
