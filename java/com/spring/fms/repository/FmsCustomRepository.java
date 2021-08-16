package com.spring.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.fms.model.Customization;

public interface FmsCustomRepository extends JpaRepository<Customization, Long>{

}
