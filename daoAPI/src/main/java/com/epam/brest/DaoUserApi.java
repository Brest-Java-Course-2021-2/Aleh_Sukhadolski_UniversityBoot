package com.epam.brest;

import com.epam.brest.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest.model")
public interface DaoUserApi {


    public List<User> getAllUsers();

    public User getUserByName(String name);

    public User getUserByEmail(String email);

    public User getUserById(Integer id);

    public User saveAndUpdateUser(User user);

    public void deleteUser(User user);

    public void deleteUserById(Integer id);

}
