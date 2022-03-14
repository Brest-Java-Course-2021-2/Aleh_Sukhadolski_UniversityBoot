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
    private DaoGroupApi daoGroupe;

    @Autowired
    private DaoLectorApi daoUser;

    @Autowired
    private DaoRequestFromLectorApi daoRequest;


    @Override
    public List<String> getAllGroupeNamesService() {
        return (List<String>) daoGroupe.getAllGroupsNames();
    }

    @Override
    public List<Group> getAllGroupesService() {
        return (List<Group>) daoGroupe.getAllGroups();
    }

    @Override
    public String deleteGroupeByNameService(String name) {
        List<Lector> users = daoUser.getAllLectors();
        List <RequestFromLector> requests;
        for (Lector user : users){
            requests = daoRequest.getAllRequestsFromLectorByIdLector(user.getIdLector());
            for (RequestFromLector request : requests){
                if (request.getGroup().equals(name)){
                    daoRequest.deleteRequestFromLector(request);
                }
            }
        }
        return (String) daoGroupe.deleteGroupByName(name);
    }

    @Override
    public Group insertNewGroupeService(String newName) {
        List<Lector> users = daoUser.getAllLectors();
        List<Integer> idUsers = new ArrayList<>();
        for (Lector user : users){ idUsers.add(user.getIdLector()); }
        daoRequest.createRequestsForLectorsWhenCreateNewGroup(newName, idUsers );
        return (Group) daoGroupe.insertNewGroup(newName);
    }

    @Override
    public Group getGroupeByNameService(String name) {
        return (Group) daoGroupe.getGroupByName(name);
    }

    @Override
    public Group updateGroupeNameService(String newName, String oldName) {
        Group groupe = (Group) daoGroupe.updateGroup(newName, oldName);
        List<Lector> users = daoUser.getAllLectors();
        List <RequestFromLector> requests;
        for (Lector user : users){
            requests = daoRequest.getAllRequestsFromLectorByIdLector(user.getIdLector());
            for (RequestFromLector request : requests){
                if (request.getGroup().equals(oldName)){
                    request.setGroup(newName);
                    daoRequest.updateRequestFromLector(request);
                }
            }

        }
        return (Group) groupe;
    }
}
