package com.epam.brest;

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
public class GroupServiceImpl implements GroupServiceApi {

    private final Logger logger = LogManager.getLogger(GroupServiceImpl.class);

    @Autowired
    private DaoGroupApi daoGroup;

    @Autowired
    private DaoLectorApi daoLector;

    @Autowired
    private DaoRequestFromLectorApi daoRequestFromLector;

    @Override
    public List<String> getAllGroupNamesService() {
        logger.info("Get all groups by the nameGroup service");
        return (List<String>) daoGroup.getAllGroupsNames();
    }

    @Override
    public List<com.epam.brest.Group> getAllGroupsService() {
        logger.info("Get all groups service");
        return (List<Group>) daoGroup.getAllGroups();
    }

    @Override
    public Integer deletegroupByIdService(Integer idGroup) {
        logger.info("Delete group by Id service");
        return daoGroup.deleteGroupById(idGroup);
    }
/*

    @Override
    public String deleteGroupByGroupNameService(String name) {
        logger.info("Delete group by the nameGroup service");
        List<Lector> lectors = daoLector.getAllLectors();
        List <RequestFromLector> requestsFromlector;
        for (Lector user : lectors){
            requestsFromlector = daoRequestFromLector.getAllRequestsFromLectorByIdLector(user.getIdLector());
            for (RequestFromLector request : requestsFromlector){
                if (request.getGroup().equals(name)){
                    daoRequestFromLector.deleteRequestFromLector(request);
                }
            }
        }
        return (String) daoGroup.deleteGroupByName(name);
    }
*/

    @Override
    public Group createNewGroupService(String newName) {
        logger.info("Create new group service");
        List<Lector> lectors = daoLector.getAllLectors();
        List<Integer> idLectors = new ArrayList<>();
        for (Lector lector : lectors){ idLectors.add(lector.getIdLector()); }
        daoRequestFromLector.createRequestsForLectorsWhenCreateNewGroup(newName, idLectors);
        return (Group) daoGroup.insertNewGroup(newName);
    }

    @Override
    public Group getGroupByGroupNameService(String name) {
        logger.info("Get group by the nameGroup service " + name);
        return (Group) daoGroup.getGroupByName(name);
    }

    @Override
    public Group updateGroupNameService(String newName, String oldName) {
        logger.info("Update group service " + newName + " to " + oldName);
        Group group = (Group) daoGroup.updateGroup(newName, oldName);
        List<Lector> lectors = daoLector.getAllLectors();
        List <RequestFromLector> requestsFromLector;
        for (Lector lector : lectors){
            requestsFromLector = daoRequestFromLector.getAllRequestsFromLectorByIdLector(lector.getIdLector());
            for (RequestFromLector request : requestsFromLector){
                if (request.getGroup().equals(oldName)){
                    request.setGroup(newName);
                    daoRequestFromLector.updateRequestFromLector(request);
                }
            }
        }
        return (Group) group;
    }

    @Override
    public Group getGroupById(Integer idGroup) {
        return (Group) daoGroup.getGroupByid(idGroup);
    }

}
