package com.epam.brest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ComponentScan("com.epam.brest")
public class GroupeDao implements DaoGroupeApi {

    private final Logger logger = LogManager.getLogger(GroupeDao.class);

    @Autowired
    private GroupeJpaRepository groupeRepository;

    @Override
    public List<String> getAllGroupeNames() {
        logger.info("GET ALL GROUPE NAMES {}");
        return groupeRepository.getAllGroupesByName();
    }

    @Override
    public List<Groupe> getAllGroupes() {
        logger.info("GET ALL GROUPES  {}");
        return groupeRepository.getAllGroupes();
    }

    @Override
    public String deleteGroupeByName(String groupeName) {
        logger.info("DELETE GROUPE BY the NAME {}");
        return groupeRepository.deleteGroupeByName(groupeName);
    }

    @Override
    public Groupe insertNewGroupe(String newNameOfGroupe) {
        logger.info("CREATE NEW GROUPE {}");
        return groupeRepository.insertNewGroupe(newNameOfGroupe);
    }

    @Override
    public Groupe getGroupeByName(String nameGroupe) {
        logger.info("GET GROUPE BY NAME {}");
        return groupeRepository.getGroupeByName(nameGroupe);
    }

    @Override
    public Groupe updateGroupeName(String newName, String oldName) {
        logger.info("UPDATE GROUPE.NAME BY NEW NAME {}");
        return groupeRepository.updateGroupeByName(newName, oldName);
    }
}
