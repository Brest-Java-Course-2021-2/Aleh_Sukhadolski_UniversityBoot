package com.epam.brest.service;

import com.epam.brest.daoAPI.DaoGroupeApi;
import com.epam.brest.daoAPI.DaoRequestApi;
import com.epam.brest.daoAPI.DaoUserApi;
import com.epam.brest.model.User;
import com.epam.brest.serviceapi.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@ComponentScan("com.epam.brest.*")
@EntityScan("com.epam.brest.model")
@Service
public class UserServiceImpl implements UserServiceApi {

    @Autowired
    private DaoUserApi daoUser;

    @Autowired
    private DaoRequestApi daoRequest;

    @Autowired
    private DaoGroupeApi daoGroupe;


    @Override
    public List<User> getAllUsersService() {
        return (List<User>) daoUser.getAllUsers();
    }

    @Override
    public List<User> getUserByNameService(String name) {
        return (List<User>) daoUser.getUserByName(name);
    }

    @Override
    public List<User> getUserByEmailService(String email)
    {
        return (List<User>) daoUser.getUserByEmail(email);
    }

    @Override
    public User getUserByIdService(Integer id)
    {
        return (User) daoUser.getUserById(id);
    }

    @Override
    public void deleteUserByIdService(Integer id) {
        daoRequest.deleteAllRequestsOfUser(id);
        daoUser.deleteUserById(id);
    }

    @Override
    public void deleteUserService(User user)
    {
        daoRequest.deleteAllRequestsOfUser(user.getId());
        daoUser.deleteUserById(user.getId());
    }

    @Override
    public User saveNewUserService(User user) {
        daoUser.saveAndUpdateUser(user);
        List <String> groupes = daoGroupe.getAllGroupeNames();
        user = daoUser.getUserByName(user.getName()).get(0);
        daoRequest.saveRequestsForNewUser(user.getId(), groupes);
        return (User) user;
    }

    @Override
    public User updateUserService(User user) {
        return (User) daoUser.saveAndUpdateUser(user);
    }
}
