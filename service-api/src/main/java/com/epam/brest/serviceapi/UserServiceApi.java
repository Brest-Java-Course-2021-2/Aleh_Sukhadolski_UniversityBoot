package com.epam.brest.serviceapi;

import com.epam.brest.model.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest.model")
public interface UserServiceApi {

    List<User> getAllUsersService();

    List<User> getUserByNameService(String name);

    List<User> getUserByEmailService(String email);

    User getUserByIdService(Integer id);

    User saveNewUserService(User user);

    User updateUserService (User user);

    void deleteUserService(User user);

    void deleteUserByIdService(Integer id);

}
