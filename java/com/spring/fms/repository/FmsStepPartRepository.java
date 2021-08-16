package com.spring.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.fms.model.StepPart;

public interface FmsStepPartRepository extends JpaRepository<StepPart, Long> {

}
