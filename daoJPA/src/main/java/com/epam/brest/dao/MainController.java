package com.epam.brest.dao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demo")
public class MainController {
/*
    @Autowired
    private UserDao userDao;

    @Autowired
    private RequestDao requestDao;

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser(@RequestParam String name
            , @RequestParam String login
            , @RequestParam String password
            , @RequestParam String email) {
        User user = new User(name, login, password, email);
        userDao.saveAndUpdateUser(user);
        user = userDao.getUserByName(user.getName()).get(0);
        List<String> groupes = Arrays.asList(new String[]{"e1", "e2", "e3", "e4", "e5"});
        requestDao.saveRequestsForNewUser(user.getId(), groupes);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @PostMapping(path = "/username")
    public @ResponseBody
    Iterable<User> findUsersByName(@RequestParam String name) {
        return userDao.getUserByName(name);
    }

    @PostMapping(path = "/useremail")
    public @ResponseBody
    Iterable<User> findUsersByEmail(@RequestParam String email) {
        return userDao.getUserByEmail(email);
    }

    @PostMapping(path = "/update") // Map ONLY POST Requests
    public @ResponseBody
    String updateUser(
            @RequestParam String id
            , @RequestParam String name
            , @RequestParam String login
            , @RequestParam String password
            , @RequestParam String email) {
        User user = new User(Integer.parseInt(id), name, login, password, email);
        userDao.saveAndUpdateUser(user);
        return "Updated";
    }

    @PostMapping(path = "/userdelete")
    public @ResponseBody
    String deleteUser(
              @RequestParam String id
            , @RequestParam String name
            , @RequestParam String login
            , @RequestParam String password
            , @RequestParam String email) {
        User user = new User(Integer.parseInt(id), name, login, password, email);
        userDao.deleteUser(user);
        return "Deleted User id = " + user.getId();
    }*/
}
