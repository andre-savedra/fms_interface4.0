package com.spring.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.fms.model.User;

public interface FmsUserRepository extends JpaRepository<User, Long> {

}
