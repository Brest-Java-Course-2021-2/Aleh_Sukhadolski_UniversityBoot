package com.epam.brest;

import com.epam.brest.serviceapi.GroupeServiceApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Component
@ComponentScan("com.epam.brest.*")
@EntityScan("com.epam.brest")
@Service
public class GroupeServiceImpl implements GroupeServiceApi {

    private final Logger logger = LogManager.getLogger(GroupeServiceImpl.class);

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
    public List<Group> getAllGroupesService() {
        return (List<Group>) daoGroupe.getAllGroupes();
    }

    @Override
    public String deleteGroupeByNameService(String name) {
        List<Lector> users = daoUser.getAllUsers();
        List <RequestFromLector> requests;
        for (Lector user : users){
            requests = daoRequest.getAllRequests(user.getIdLector());
            for (RequestFromLector request : requests){
                if (request.getGroup().equals(name)){
                    daoRequest.deleteRequest(request);
                }
            }
        }
        return (String) daoGroupe.deleteGroupeByName(name);
    }

    @Override
    public Group insertNewGroupeService(String newName) {
        List<Lector> users = daoUser.getAllUsers();
        List<Integer> idUsers = new ArrayList<>();
        for (Lector user : users){ idUsers.add(user.getIdLector()); }
        daoRequest.saveRequestsWhenNewGroupe(newName, idUsers );
        return (Group) daoGroupe.insertNewGroupe(newName);
    }

    @Override
    public Group getGroupeByNameService(String name) {
        return (Group) daoGroupe.getGroupeByName(name);
    }

    @Override
    public Group updateGroupeNameService(String newName, String oldName) {
        Group groupe = (Group) daoGroupe.updateGroupeName(newName, oldName);
        List<Lector> users = daoUser.getAllUsers();
        List <RequestFromLector> requests;
        for (Lector user : users){
            requests = daoRequest.getAllRequests(user.getIdLector());
            for (RequestFromLector request : requests){
                if (request.getGroup().equals(oldName)){
                    request.setGroup(newName);
                    daoRequest.updateRequest(request);
                }
            }

        }
        return (Group) groupe;
    }
}
