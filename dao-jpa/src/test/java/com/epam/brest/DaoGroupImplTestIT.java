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
        String[] groups = new String[]{"e1", "e2", "e3", "e4", "e5", "e6"};
        List<Group> grup = Arrays.stream(groups)
                .map(gr -> daoGroup.insertNewGroup(gr))
                .collect(Collectors.toList());
    }


    @Test
    public void testGetAllGroups() {
        logger.info("GET ALL GROUPS {}");
        List<Group> groups = (List<Group>) daoGroup.getAllGroups();
        assertTrue(groups.get(0).getGroupName() == "e1");
    }

    @Test
    public void testGetAllGroupsByNames() {
        logger.info("GET ALL GROUP NAMES{}");
        List<String> groups = (List<String>) daoGroup.getAllGroupsNames();
        assertTrue(groups.size() == 6);
    }

    @Test
    public void testAddGroup() {
        logger.info("ADD NEW GROUP {}");
        daoGroup.insertNewGroup("a1");
        List<String> groups = (List<String>) daoGroup.getAllGroupsNames();
        assertTrue(groups.size() == 7);
    }

    @Test
    public void testUpdateGroupName() {
        logger.info("UPDATE GROUP NAME {}");
        Group updatedGroup = daoGroup.updateGroup("a1", "e1");
        Group group = daoGroup.getGroupByName("a1");
        assertTrue(group.getGroupName().equals("a1")
                           && updatedGroup.getIdGroup() == group.getIdGroup());
    }


    @Test
    public void testDeleteGroupByName() {
        logger.info("DELETE GROUP BY NAME {}");
        String deletedNameGroup = daoGroup.deleteGroupByName("e1");
        assertTrue(deletedNameGroup.equals("e1"));
        deletedNameGroup = daoGroup.deleteGroupByName("e1");
        assertTrue(deletedNameGroup.equals("Is Empty"));
        Group group = daoGroup.getGroupByName("e1");
        assertTrue(group.getGroupName().equals("IsEmpty") );
    }

}
