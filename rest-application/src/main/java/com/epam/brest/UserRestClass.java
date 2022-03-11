package com.epam.brest;

import com.epam.brest.serviceapi.UserServiceApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserRestClass {

    private final Logger logger = LogManager.getLogger(UserRestClass.class);

    @Autowired
    private UserServiceApi userService;

    @GetMapping("/user/all")
    @Transactional(readOnly = true)
    public List<User> users()
    {
        logger.debug("getAllUsers({})");
        return (List<User>) userService.getAllUsersService();
    }


    @GetMapping("/user/name")
    @Transactional(readOnly = true)
    public User userByName(@RequestParam String name)
    {
        logger.debug("getUserByName({})", name);
        return (User) userService.getUserByNameService(name);
    }

    @GetMapping("/user/email")
    @Transactional(readOnly = true)
    public User userByEmail(@RequestParam String email)
    {
        logger.debug("getUserByEmail({})", email);
        return (User) userService.getUserByEmailService(email);
    }

    @GetMapping("/user/id")
    @Transactional(readOnly = true)
    public User userById(@RequestParam String id)
    {
        logger.debug("getUserById({})", id);
        return (User) userService.getUserByIdService(Integer.parseInt(id));
    }


    @PostMapping(path = "/user/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createUser(@RequestBody User user) {
        logger.debug("createUser({})", user);
        user = userService.saveNewUserService(user);
        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }


    @PutMapping(path = "/user/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> updateUser(@RequestBody User user) {
        logger.debug("updateUser({})", user);
        user = userService.saveNewUserService(user);
        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/delete/{id}", produces = {"application/json"})
    public ResponseEntity <Integer> deleteUserById(@PathVariable Integer id) {
        logger.debug("deleteUser({})", id);
        userService.deleteUserByIdService(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }


    @DeleteMapping(value = "/user/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity <Integer> deleteUser(@RequestBody User user) {
        logger.debug("deleteUser({})", user);
        userService.deleteUserService(user);
        return new ResponseEntity(user.getId(), HttpStatus.OK);
    }


}
