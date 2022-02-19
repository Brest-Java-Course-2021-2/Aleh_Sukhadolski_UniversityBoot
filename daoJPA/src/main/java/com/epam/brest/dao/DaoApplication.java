package com.epam.brest.dao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EntityScan("com.epam.brest.model")
public class DaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaoApplication.class, args);
	}

}
