package com.spring.fms.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.fms.model.User;
import com.spring.fms.service.FmsUserService;

@CrossOrigin
@Controller
public class FmsUserController {

	@Autowired
	FmsUserService userService;

	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/save_user", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public String saveUser(@RequestBody @Valid User user, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			System.out.println("DEU ERRO!");
			return "erro";
		}

		try {
			Long id = userService.findUserById(user.getId()).getId();
			System.out.println(id);

			return "existente";

		} catch (NoSuchElementException ex) {
			userService.saveUser(user);
			System.out.println("SOLICITACAO OKKK");

			return "feito";
		}
	}

	public User searchUser(User user) {
		User userFound;

		try {
			userFound = userService.findUserById(user.getId());

			return userFound;
		} catch (NoSuchElementException ex) {
			userFound = new User();
			userFound.setId(0L);
			userFound.setName("notFound");
			return userFound;
		}
	}
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/get_user", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@RequestBody User user) {

		return searchUser(user);		
	}
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/get_users", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {

		return userService.findUserAll();
	}
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/validate_user", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public String validateUser(@RequestBody User user) {
		User userFound = searchUser(user);
		
		if(userFound.getName().equals("notFound") && userFound.getId() == 0L) {
			return "notFound";
		}
		else {
			
			if(user.getPassword().equals(userFound.getPassword()))
			{
				return "userOk";
			}
			else {
				return "blocked";
			}
			
		}
		
	}
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/validate_credential_user", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public String validateCredentialUser(@RequestBody User user) {

		return "feito";
	}
	@CrossOrigin
	@RequestMapping(value = "/successRegister", method = RequestMethod.GET)
	public String getSuccessRegister() {
		System.out.println("success");
		return "successedRegister";
	}
	@CrossOrigin
	@RequestMapping(value = "/failRegister", method = RequestMethod.GET)
	public String getFailRegister() {
		System.out.println("fail");
		return "failureRegister";
	}	
	
	
	/** ------ SUCCESS LOGIN ------- **/
	@CrossOrigin
	@RequestMapping(value = "/successlogin", method = RequestMethod.GET)
	public String getSuccessLogin() {
		
		return "successedLogin";
	}

}
