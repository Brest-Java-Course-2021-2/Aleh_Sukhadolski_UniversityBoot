package com.epam.brest.rest.application;

import com.epam.brest.model.User;
import com.epam.brest.serviceapi.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/users")
    @Transactional(readOnly = true)
    public List<User> users()
    {
        return (List<User>) userService.getAllUsersService();
    }

    @PostMapping ("/user/save")
    public User saveuser(@RequestParam String name, @RequestParam String login
                       , @RequestParam String password, @RequestParam String email ){
        return userService.saveNewUserService(new User(name, login, password, email));
    }
}
