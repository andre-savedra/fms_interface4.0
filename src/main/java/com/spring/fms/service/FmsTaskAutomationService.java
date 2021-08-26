package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.TasksAutomation;

public interface FmsTaskAutomationService {
	List<TasksAutomation> findAllTaskAutomation();
	TasksAutomation findTaskAutomationById(long id);
	TasksAutomation saveTaskAutomation(TasksAutomation task);
	
}
