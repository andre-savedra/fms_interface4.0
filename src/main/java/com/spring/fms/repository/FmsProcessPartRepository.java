package com.spring.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.fms.model.ProcessPart;

public interface FmsProcessPartRepository extends JpaRepository<ProcessPart, Long> {

}
