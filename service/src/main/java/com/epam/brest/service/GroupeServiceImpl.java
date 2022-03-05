package com.epam.brest.service;

import com.epam.brest.Request;
import com.epam.brest.User;
import com.epam.brest.daoAPI.DaoGroupeApi;
import com.epam.brest.Groupe;
import com.epam.brest.daoAPI.DaoRequestApi;
import com.epam.brest.daoAPI.DaoUserApi;
import com.epam.brest.serviceapi.GroupeServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
@ComponentScan("com.epam.brest")
@EntityScan("com.epam.brest")
@Service
public class GroupeServiceImpl implements GroupeServiceApi {

    @Autowired
    private DaoGroupeApi daoGroupe;

    @Autowired
    private DaoUserApi daoUser;

    @Autowired
    private DaoRequestApi daoRequest;


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
        List<User> users = daoUser.getAllUsers();
        List <Request> requests;
        for (User user : users){
            requests = daoRequest.getAllRequests(user.getId());
            for (Request request : requests){
                if (request.getGroupe().equals(name)){
                    daoRequest.deleteRequest(request);
                }
            }
        }
        return (String) daoGroupe.deleteGroupeByName(name);
    }

    @Override
    public Groupe insertNewGroupeService(String newName) {
        List<User> users = daoUser.getAllUsers();
        List<Integer> idUsers = new ArrayList<>();
        for (User user : users){ idUsers.add(user.getId()); }
        daoRequest.saveRequestsWhenNewGroupe(newName, idUsers );
        return (Groupe) daoGroupe.insertNewGroupe(newName);
    }

    @Override
    public Groupe getGroupeByNameService(String name) {
        return (Groupe) daoGroupe.getGroupeByName(name);
    }

    @Override
    public Groupe updateGroupeNameService(String newName, String oldName) {
        Groupe groupe = (Groupe) daoGroupe.updateGroupeName(newName, oldName);
        List<User> users = daoUser.getAllUsers();
        List <Request> requests;
        for (User user : users){
            requests = daoRequest.getAllRequests(user.getId());
            for (Request request : requests){
                if (request.getGroupe().equals(oldName)){
                    request.setGroupe(newName);
                    daoRequest.updateRequest(request);
                }
            }

        }
        return (Groupe) groupe;
    }
}
