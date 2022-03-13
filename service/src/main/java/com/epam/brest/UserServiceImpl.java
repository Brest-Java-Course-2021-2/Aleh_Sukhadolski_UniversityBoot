package com.epam.brest;

import com.epam.brest.serviceapi.UserServiceApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@ComponentScan("com.epam.brest.*")
@EntityScan("com.epam.brest")
@Service
public class UserServiceImpl implements UserServiceApi {

    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private DaoUserApi daoUser;

    @Autowired
    private DaoRequestApi daoRequest;

    @Autowired
    private DaoGroupeApi daoGroupe;


    @Override
    public List<Lector> getAllUsersService() {
        return (List<Lector>) daoUser.getAllUsers();
    }

    @Override
    public Lector getUserByNameService(String name) {
        return (Lector) daoUser.getUserByName(name);
    }

    @Override
    public Lector getUserByEmailService(String email)
    {
        return (Lector) daoUser.getUserByEmail(email);
    }

    @Override
    public Lector getUserByIdService(Integer id)
    {
        return (Lector) daoUser.getUserById(id);
    }

    @Override
    public void deleteUserByIdService(Integer id) {
        daoRequest.deleteAllRequestsOfUser(id);
        daoUser.deleteUserById(id);
    }

    @Override
    public void deleteUserService(Lector user)
    {
        daoRequest.deleteAllRequestsOfUser(user.getIdLector());
        daoUser.deleteUserById(user.getIdLector());
    }

    @Override
    public Lector saveNewUserService(Lector user) {
        user = daoUser.saveAndUpdateUser(user);
        List <String> groupes = daoGroupe.getAllGroupeNames();
        daoRequest.saveRequestsForNewUser(user.getIdLector(), groupes);
        return (Lector) user;
    }

    @Override
    public Lector updateUserService(Lector user) {
        return (Lector) daoUser.saveAndUpdateUser(user);
    }
}
