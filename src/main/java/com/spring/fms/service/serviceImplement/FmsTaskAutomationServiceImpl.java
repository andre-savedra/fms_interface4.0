package com.spring.fms.service.serviceImplement;

import java.util.List;

import com.spring.fms.model.TasksAutomation;
import com.spring.fms.repository.FmsTasksAutomationRepository;
import com.spring.fms.service.FmsTaskAutomationService;

public class FmsTaskAutomationServiceImpl implements FmsTaskAutomationService{

	FmsTasksAutomationRepository taskAutomationRepository;
	
	@Override
	public List<TasksAutomation> findAllTaskAutomation() {
		return taskAutomationRepository.findAll();
	}

	@Override
	public TasksAutomation findTaskAutomationById(long id) {
		return taskAutomationRepository.findById(id).get();
	}

	@Override
	public TasksAutomation saveTaskAutomation(TasksAutomation task) {
		return taskAutomationRepository.save(task);
	}

}
