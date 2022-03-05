package com.epam.brest.rest.application;

import com.epam.brest.serviceapi.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ServiceRestClass {

    @Autowired
    private UserServiceApi userService;

    public ServiceRestClass(UserServiceApi userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String home() {
        return "HELLO!!!!!!!";
    }

    @GetMapping("/version")
    public String appversion() {
        return "version-0.0.1-SNAPSHOT";
    }



}
