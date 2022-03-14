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
    private DaoLectorApi daoUser;

    @Autowired
    private DaoRequestFromLectorApi daoRequest;

    @Autowired
    private DaoGroupApi daoGroupe;


    @Override
    public List<Lector> getAllUsersService() {
        return (List<Lector>) daoUser.getAllLectors();
    }

    @Override
    public Lector getUserByNameService(String name) {
        return (Lector) daoUser.getLectorByName(name);
    }

    @Override
    public Lector getUserByEmailService(String email)
    {
        return (Lector) daoUser.getLectorByEmail(email);
    }

    @Override
    public Lector getUserByIdService(Integer id)
    {
        return (Lector) daoUser.getLectorById(id);
    }

    @Override
    public void deleteUserByIdService(Integer id) {
        daoRequest.deleteAllRequestsFromLector(id);
        daoUser.deleteLectorById(id);
    }

    @Override
    public void deleteUserService(Lector user)
    {
        daoRequest.deleteAllRequestsFromLector(user.getIdLector());
        daoUser.deleteLectorById(user.getIdLector());
    }

    @Override
    public Lector saveNewUserService(Lector user) {
        user = daoUser.saveOrUpdateLector(user);
        List <String> groupes = daoGroupe.getAllGroupsNames();
        daoRequest.createEmptyRequestsForNewLector(user.getIdLector(), groupes);
        return (Lector) user;
    }

    @Override
    public Lector updateUserService(Lector user) {
        return (Lector) daoUser.saveOrUpdateLector(user);
    }
}
