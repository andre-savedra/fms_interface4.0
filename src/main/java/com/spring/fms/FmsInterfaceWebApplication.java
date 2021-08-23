package com.spring.fms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FmsInterfaceWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FmsInterfaceWebApplication.class, args);
		//ManagerProduction manager = new ManagerProduction();
	}

}
