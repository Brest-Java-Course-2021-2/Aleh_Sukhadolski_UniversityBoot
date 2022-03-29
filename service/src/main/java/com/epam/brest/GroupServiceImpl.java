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
    public List<Group> getAllGroupsService() {
        logger.info("Get all groups service");
        return (List<Group>) daoGroup.getAllGroups();
    }

    @Override
    public Integer deletegroupByIdService(Integer idGroup) {
        logger.info("Delete group by Id service");
        Group group = daoGroup.getGroupByid(idGroup);
        daoRequestFromLector.deleteFromAllLectorsRequestsWhenDeletedGroup(group.getGroupName());
        return daoGroup.deleteGroupById(idGroup);
    }

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
        daoRequestFromLector.deleteFromAllLectorsRequestsWhenDeletedGroup(name);
        return (Group) daoGroup.getGroupByName(name);
    }

    @Override
    public Group updateGroupNameService(String newName, String oldName) {
        logger.info("Update group service " + newName + " to " + oldName);
        Group group = daoGroup.updateGroup(newName, oldName);
        daoRequestFromLector.updateAllLectorsRequestsWhenChangedGroup(newName, oldName);
        return group;
    }

    @Override
    public Group getGroupById(Integer idGroup) {
        return (Group) daoGroup.getGroupByid(idGroup);
    }

}
