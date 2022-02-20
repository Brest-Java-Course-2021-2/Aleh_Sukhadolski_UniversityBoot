package com.epam.brest.daoAPI;

import com.epam.brest.model.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest.model")
public interface DaoUserApi {


    public List<User> getAllUsers();

    public List<User> getUserByName(String name);

    public List<User> getUserByEmail(String email);

    public User getUserById(Integer id);

    public User saveAndUpdateUser(User user);

    public String deleteUser(User user);

    public String deleteUserById(Integer id);

}
