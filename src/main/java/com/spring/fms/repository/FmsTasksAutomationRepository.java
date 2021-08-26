package com.spring.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.fms.model.TasksAutomation;

public interface FmsTasksAutomationRepository extends JpaRepository<TasksAutomation, Long>{

}
