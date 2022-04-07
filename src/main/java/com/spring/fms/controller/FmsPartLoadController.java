package com.spring.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.fms.model.OrderType;
import com.spring.fms.model.Part;
import com.spring.fms.service.FmsOrderTypeService;
import com.spring.fms.service.FmsPartService;

@Controller
public class FmsPartLoadController {

	@Autowired
	FmsPartService partService;
	
	@Autowired
	FmsOrderTypeService orderTypeService;

	/************* REQUEST ALL ORDER TYPE   ***********/
	@ResponseBody
	@PostMapping(value = "/load_all_order_type", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderType> getAllDataOrderType() {

		return orderTypeService.findOrderTypeAll();
	}
	
	
	
	/************* REQUEST ALL PARTS   ***********/
	@ResponseBody
	@PostMapping(value = "/load_all_parts", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Part> getAllData() {

		return partService.findPartAll();
	}
	
	/************* REQUEST TOTAL COUNT   ***********/
	@ResponseBody
	@PostMapping(value = "/count_parts", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long getAllPartCount() {

		return partService.count();
	}
	
	/************* REQUEST COUNT BY ORDER TYPE (MACHINE)   ***********/
	
	@ResponseBody
	@PostMapping(value = "/count_parts_order_type", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long getPartCountMachine(@RequestBody OrderType type_id, BindingResult result,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			System.out.println("DEU ERRO NO getDataPartOrderType!");
			return null;
		}

		return (long)partService.findAllByType(type_id).size();
	}
	
	
	/************* REQUEST PARTS BY ORDER TYPE (MACHINE)   ***********/
	 
	@ResponseBody
	@PostMapping(value = "/load_part_order_type", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Part> getAllPartMachine(@RequestBody OrderType type_id, BindingResult result,
			RedirectAttributes attributes) {
		/*System.out.println("O TYPE É:");
		System.out.println(type_id.getType());
		System.out.println(type_id.getId());*/

		if (result.hasErrors()) {
			System.out.println("DEU ERRO NO getDataPartByType!");
			return null;
		}		
		
		return partService.findAllByType(type_id);		
	}
	
	



}
