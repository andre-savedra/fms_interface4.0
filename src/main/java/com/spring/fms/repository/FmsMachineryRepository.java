package com.spring.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.fms.model.Machinery;

public interface FmsMachineryRepository extends JpaRepository<Machinery, Long> {

}
