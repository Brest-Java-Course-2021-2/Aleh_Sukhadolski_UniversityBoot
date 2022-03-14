package com.epam.brest;

import com.epam.brest.serviceapi.LectorServiceApi;
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
public class LectorServiceImpl implements LectorServiceApi {

    private final Logger logger = LogManager.getLogger(LectorServiceImpl.class);

    @Autowired
    private DaoLectorApi daoLector;

    @Autowired
    private DaoRequestFromLectorApi daoRequestFromLector;

    @Autowired
    private DaoGroupApi daoGroup;


    @Override
    public List<Lector> getAllLectorsService() {
        return (List<Lector>) daoLector.getAllLectors();
    }

    @Override
    public Lector getLectorByLectorsNameService(String name) {
        return (Lector) daoLector.getLectorByName(name);
    }

    @Override
    public Lector getLectorByEmailService(String email)
    {
        return (Lector) daoLector.getLectorByEmail(email);
    }

    @Override
    public Lector getLectorByIdLectorService(Integer id)
    {
        return (Lector) daoLector.getLectorById(id);
    }

    @Override
    public void deleteLectorByIdLectorService(Integer id) {
        daoRequestFromLector.deleteAllRequestsFromLector(id);
        daoLector.deleteLectorById(id);
    }

    @Override
    public void deleteLectorService(Lector user)
    {
        daoRequestFromLector.deleteAllRequestsFromLector(user.getIdLector());
        daoLector.deleteLectorById(user.getIdLector());
    }

    @Override
    public Lector createNewLectorService(Lector user) {
        user = daoLector.saveOrUpdateLector(user);
        List <String> groupes = daoGroup.getAllGroupsNames();
        daoRequestFromLector.createEmptyRequestsForNewLector(user.getIdLector(), groupes);
        return (Lector) user;
    }

    @Override
    public Lector updateLectorService(Lector user) {
        return (Lector) daoLector.saveOrUpdateLector(user);
    }
}