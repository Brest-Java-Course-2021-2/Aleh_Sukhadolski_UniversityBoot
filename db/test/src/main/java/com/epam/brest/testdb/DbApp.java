package com.epam.brest.testdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.epam.brest.*")
public class DbApp {

    public static void main(String[] args) {
        SpringApplication.run(DbApp.class, args);
    }

}