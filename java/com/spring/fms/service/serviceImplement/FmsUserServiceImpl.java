package com.spring.fms.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fms.model.User;
import com.spring.fms.repository.FmsUserRepository;
import com.spring.fms.service.FmsUserService;

@Service
public class FmsUserServiceImpl implements FmsUserService{

	@Autowired
	FmsUserRepository userRepository;
	
	@Override
	public List<User> findUserAll() {		
		return userRepository.findAll();
	}

	@Override
	public User findUserById(long id) {		
		return userRepository.findById(id).get();
	}

	@Override
	public User saveUser(User user) {		
		return userRepository.save(user);
	}

}
