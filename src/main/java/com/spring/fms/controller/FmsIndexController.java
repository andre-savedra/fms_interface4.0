package com.spring.fms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin
@Controller
public class FmsIndexController {

	@CrossOrigin
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndex() {
		
		return "index";
	}
	
	@CrossOrigin
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String getMain() {
		
		return "main";
	}
	
	@CrossOrigin
	@RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public String getSignIn() {		
		return "signIn";
	}
	
	
	/*@RequestMapping(value = "/sendText", method = RequestMethod.GET)
	public String getSendText() {
		EmailSender emailSender = new EmailSender();;
		emailSender.sendMail("19993805639", "Teste Andre");
		System.out.println("Mandou msg");
		return "index";
	}*/
	
	
}