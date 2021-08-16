package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.Model;

public interface FmsModelService {
	List<Model> findModelAll();
	Model findModelById(long id);
	Model saveModel(Model model);
}
