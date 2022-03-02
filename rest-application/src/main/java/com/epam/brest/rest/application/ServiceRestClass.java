package com.epam.brest.rest.application;

import com.epam.brest.model.User;
import com.epam.brest.serviceapi.UserServiceApi;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ServiceRestClass {

    private UserServiceApi userService;

    @GetMapping("/hello")
    public String home() {
        return "HELLO!!!!!!!";
    }

    @GetMapping("/version")
    public String appversion() {
        return "version-0.0.1-SNAPSHOT";
    }

    @GetMapping("/users")
    @Transactional(readOnly = true)
    public List<User> users()
    {
        return (List<User>) userService.getAllUsersService();
    }
}
