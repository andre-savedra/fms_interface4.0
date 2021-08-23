package com.spring.fms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FmsIndexController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndex() {
		
		return "index";
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String getMain() {
		
		return "main";
	}
	
	@RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public String getSignIn() {		
		return "signIn";
	}
	
	
}