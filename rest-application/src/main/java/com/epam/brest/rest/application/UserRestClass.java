package com.epam.brest.rest.application;

import com.epam.brest.model.User;
import com.epam.brest.serviceapi.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserRestClass {

    @Autowired
    private UserServiceApi userService;

    @GetMapping("/user/all")
    @Transactional(readOnly = true)
    public List<User> users()
    {
        return (List<User>) userService.getAllUsersService();
    }


    @GetMapping("/user/name")
    @Transactional(readOnly = true)
    public User userByName(@RequestParam String name)
    {
        return (User) userService.getUserByNameService(name);
    }

    @GetMapping("/user/email")
    @Transactional(readOnly = true)
    public User userByEmail(@RequestParam String email)
    {
        return (User) userService.getUserByEmailService(email);
    }

    @GetMapping("/user/id")
    @Transactional(readOnly = true)
    public User userById(@RequestParam String id)
    {
        return (User) userService.getUserByIdService(Integer.parseInt(id));
    }

    @GetMapping("/user/save")
    public User saveuser(@RequestParam String name, @RequestParam String login
                       , @RequestParam String password, @RequestParam String email ){
        return userService.saveNewUserService(new User(name, login, password, email));
    }

    @GetMapping("/user/update")
    public User updateuser(@RequestParam String id, @RequestParam String name, @RequestParam String login
            , @RequestParam String password, @RequestParam String email ){
        return userService.saveNewUserService(new User(Integer.parseInt(id), name, login, password, email));
    }

    @GetMapping("/user/delete")
    public void deleteuser(@RequestParam String id, @RequestParam String name, @RequestParam String login
            , @RequestParam String password, @RequestParam String email ){
        userService.deleteUserService(new User(Integer.parseInt(id), name, login, password, email));
    }

    @GetMapping("/user/delete/id")
    public void deleteuser(@RequestParam String id){
        userService.deleteUserByIdService(Integer.parseInt(id));
    }
}
