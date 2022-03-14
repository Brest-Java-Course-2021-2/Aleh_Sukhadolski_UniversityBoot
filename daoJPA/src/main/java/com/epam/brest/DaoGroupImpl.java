package com.epam.brest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ComponentScan("com.epam.brest")
public class DaoGroupImpl implements DaoGroupApi {

    private final Logger logger = LogManager.getLogger(DaoGroupImpl.class);

    @Autowired
    private GroupJpaRepository groupeRepository;

    @Override
    public List<String> getAllGroupsNames() {
        logger.info("GET ALL GROUPE NAMES {}");
        return groupeRepository.getAllGroupsNames();
    }

    @Override
    public List<Group> getAllGroups() {
        logger.info("GET ALL GROUPES  {}");
        return groupeRepository.getAllGroups();
    }

    @Override
    public String deleteGroupByName(String groupeName) {
        logger.info("DELETE GROUPE BY the NAME {}");
        return groupeRepository.deleteGroupByGroupName(groupeName);
    }

    @Override
    public Group insertNewGroup(String newNameOfGroupe) {
        logger.info("CREATE NEW GROUPE {}");
        return groupeRepository.insertNewGroup(newNameOfGroupe);
    }

    @Override
    public Group getGroupByName(String nameGroupe) {
        logger.info("GET GROUPE BY NAME {}");
        return groupeRepository.getGroupeByGroupName(nameGroupe);
    }

    @Override
    public Group updateGroup(String newName, String oldName) {
        logger.info("UPDATE GROUPE.NAME BY NEW NAME {}");
        return groupeRepository.updateGroupByGroupName(newName, oldName);
    }
}
