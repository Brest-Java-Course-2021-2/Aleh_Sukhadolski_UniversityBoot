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
        return (List<String>) daoGroupe.getAllGroupeNames();
    }

    @Override
    public List<Groupe> getAllGroupesService() {
        return (List<Groupe>) daoGroupe.getAllGroupes();
    }

    @Override
    public String deleteGroupeByNameService(String name) {
        return (String) daoGroupe.deleteGroupeByName(name);
    }

    @Override
    public Groupe insertNewGroupeService(String newName) {
        return (Groupe) daoGroupe.insertNewGroupe(newName);
    }

    @Override
    public Groupe getGroupeByNameService(String name) {
        return (Groupe) daoGroupe.getGroupeByName(name);
    }

    @Override
    public Groupe updateGroupeNameService(String newName, String oldName) {
        return (Groupe) daoGroupe.updateGroupeName(newName, oldName);
    }
}
