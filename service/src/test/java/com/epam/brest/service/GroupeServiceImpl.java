package com.epam.brest.service;

import com.epam.brest.daoAPI.DaoGroupeApi;
import com.epam.brest.model.Groupe;
import com.epam.brest.serviceapi.GroupeServiceApi;
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
public class GroupeServiceImpl implements GroupeServiceApi {
    @Autowired
    private DaoGroupeApi daoGroupe;

    @Override
    public List<String> getAllGroupeNamesService() {
        return null;
    }

    @Override
    public List<Groupe> getAllGroupesService() {
        return null;
    }

    @Override
    public String deleteGroupeByNameService(String name) {
        return null;
    }

    @Override
    public Groupe insertNewGroupeService(String newName) {
        return null;
    }

    @Override
    public Groupe getGroupeByNameService(String name) {
        return null;
    }

    @Override
    public Groupe updateGroupeNameService(String newName, String oldName) {
        return null;
    }
}
