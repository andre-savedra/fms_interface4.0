package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.User;

public interface FmsUserService {

	List<User> findUserAll();
	User findUserById(long id);
	User saveUser(User user);
}
