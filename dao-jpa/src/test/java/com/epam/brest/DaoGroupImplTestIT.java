package com.epam.brest;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootApplication
@SpringBootTest (classes= { DaoLectorImpl.class, DaoRequestFromLectorImpl.class, DaoGroupImpl.class})
@ComponentScan("com.epam.brest")
@EntityScan("com.epam.brest")
@Transactional()
public class DaoGroupImplTestIT {

    private final Logger logger = LogManager.getLogger(DaoGroupImplTestIT.class);

    @Autowired
    private DaoGroupApi daoGroup;

    @BeforeEach
    public void setUp() {
        String[] groupes = new String[]{"e1", "e2", "e3", "e4", "e5", "e6"};
        List<Group> grup = Arrays.stream(groupes)
                .map(gr -> daoGroup.insertNewGroup(gr))
                .collect(Collectors.toList());
    }


    @Test
    public void testGetAllGroupes() {
        logger.info("GET ALL GROUPES {}");
        List<Group> groupes = (List<Group>) daoGroup.getAllGroups();
        assertTrue(groupes.get(0).getGroupName() == "e1");
    }

    @Test
    public void testGetAllGroupesByNames() {
        logger.info("GET ALL GROUPE NAMES{}");
        List<String> groupes = (List<String>) daoGroup.getAllGroupsNames();
        assertTrue(groupes.size() == 6);
    }

    @Test
    public void testAddGroupe() {
        logger.info("ADD NEW GROUPE {}");
        daoGroup.insertNewGroup("a1");
        List<String> groupes = (List<String>) daoGroup.getAllGroupsNames();
        assertTrue(groupes.size() == 7);
    }

    @Test
    public void testUpdateGroupeName() {
        logger.info("UPDATE GROUPE NAME {}");
        Group updatedGroupe = daoGroup.updateGroup("a1", "e1");
        Group groupe = daoGroup.getGroupByName("a1");
        assertTrue(groupe.getGroupName().equals("a1") && updatedGroupe.getIdGroup() == groupe.getIdGroup());
    }


    @Test
    public void testDeleteGroupeByName() {
        logger.info("DELETE GROUPE BY NAME {}");
        String deletedNameGroupe = daoGroup.deleteGroupByName("e1");
        assertTrue(deletedNameGroupe.equals("e1"));
        deletedNameGroupe = daoGroup.deleteGroupByName("e1");
        assertTrue(deletedNameGroupe.equals("Is Empty"));
        Group groupe = daoGroup.getGroupByName("e1");
        assertTrue(groupe.getGroupName().equals("IsEmpty") );
    }

}
