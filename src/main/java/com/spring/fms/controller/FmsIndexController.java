package com.spring.fms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.fms.utils.EmailSender;

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
	
	
	/*@RequestMapping(value = "/sendText", method = RequestMethod.GET)
	public String getSendText() {
		EmailSender emailSender = new EmailSender();;
		emailSender.sendMail("19993805639", "Teste Andre");
		System.out.println("Mandou msg");
		return "index";
	}*/
	
	
}