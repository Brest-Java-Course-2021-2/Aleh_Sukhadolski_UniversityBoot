package com.epam.brest;

import com.epam.brest.serviceapi.GroupServiceApi;
import com.epam.brest.serviceapi.RequestFromLectorServiceApi;
import com.epam.brest.serviceapi.LectorServiceApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
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

@SpringBootApplication
@SpringBootTest (classes= { LectorServiceImpl.class, RequestFromLectorServiceImpl.class, GroupServiceImpl.class
                          , DaoLectorImpl.class, DaoRequestFromLectorImpl.class, DaoGroupImpl.class})
@ComponentScan("com.epam.brest.*")
@EntityScan("com.epam.brest")
@Transactional()
public class GroupServiceImplTestIT {

    private final Logger logger = LogManager.getLogger(GroupServiceImplTestIT.class);

    @Autowired
    private GroupServiceApi groupService;

    @Autowired
    private LectorServiceApi lectorService;

    @Autowired
    private RequestFromLectorServiceApi requestFromLectorService;


    @BeforeEach
    public void setUp() {
        String[] groupes = new String[]{"e1", "e2", "e3", "e4", "e5", "e6"};
        List<Group> grup = Arrays.stream(groupes)
                .map(gr -> groupService.createNewGroupService(gr))
                .collect(Collectors.toList());
        Lector user = lectorService.createNewLectorService(new Lector("TOMMY", "tom", "1111", "iuy@aa.com"));
    }

    @Test
    public void isGroupesTest() {
        logger.info("GET ALL GROUPES {}");
        List<String> groupes = groupService.getAllGroupNamesService();
        Assertions.assertTrue(groupes.size() == 6);
        List<Group> group = groupService.getAllGroupsService();
        Assertions.assertTrue(group.size() == 6);
    }

    @Test
    public void isInsertNewGroupeTest() {
        logger.info("INSERT NEW GROUPE {}");
        Lector user = lectorService.getLectorByLectorsNameService("TOMMY");
        List<RequestFromLector> requests = requestFromLectorService.getAllRequestsFromLectorService(user.getIdLector());
        Assertions.assertTrue(requests.size() == 6);
        Group groupe = groupService.createNewGroupService("e7");
        Assertions.assertTrue(groupe.getGroupName().equals("e7"));
        List<Group> group = groupService.getAllGroupsService();
        Assertions.assertTrue(group.size() == 7);
        user = lectorService.getLectorByLectorsNameService("TOMMY");
        requests = requestFromLectorService.getAllRequestsFromLectorService(user.getIdLector());
        Assertions.assertTrue(requests.size() == 7);

    }


    @Test
    public void isUpdateGroupeNameTest() {
        logger.info("UPDATE GROUPE {}");
        groupService.updateGroupNameService("w1", "e6");
        Group groupe = groupService.getGroupByGroupNameService("w1");
        Assertions.assertTrue(groupe.getGroupName().equals("w1"));
        groupe = groupService.getGroupByGroupNameService("e6");
        Assertions.assertFalse(groupe.getGroupName().equals("e6"));
        List<Group> group = groupService.getAllGroupsService();
        Assertions.assertTrue(group.size() == 6);
        Lector user = lectorService.getLectorByLectorsNameService("TOMMY");
        List<RequestFromLector> requests = requestFromLectorService.getAllRequestsFromLectorService(user.getIdLector());
        boolean ifExist = false;
        boolean ifNotExist = true;
        for (RequestFromLector request : requests){
            if (request.getGroup().equals("w1")){ ifExist = true; }
            if (request.getGroup().equals("e6")){ ifNotExist = false; }
        }
        Assertions.assertTrue(ifExist);
        Assertions.assertTrue(ifNotExist);
        requests = requestFromLectorService.getAllRequestsFromLectorService(user.getIdLector());
        Assertions.assertTrue(requests.size() == 6);
    }

    @Test
    public void isDeleteGroupeTest() {
        logger.info("DELETE GROUPE {}");
        String result = groupService.deleteGroupByGroupNameService("e1");
        Assertions.assertTrue(result.equals("e1"));
        Group groupe = groupService.getGroupByGroupNameService("e1");
        Assertions.assertTrue(groupe.getIdGroup() == 0);

    }
}