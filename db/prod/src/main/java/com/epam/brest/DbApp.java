package com.epam.brest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@AutoConfigurationPackage
@ComponentScan("com.epam.brest.*")
//@EntityScan("com.epam.brest.model")
public class DbApp {

    public static void main(String[] args) {
        SpringApplication.run(DbApp.class, args);
    }

}

