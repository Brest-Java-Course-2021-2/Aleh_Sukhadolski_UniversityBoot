package com.epam.brest;

import com.epam.brest.serviceapi.GroupServiceApi;
import com.epam.brest.serviceapi.RequestFromLectorServiceApi;
import com.epam.brest.serviceapi.LectorServiceApi;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;


import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootApplication
@SpringBootTest (classes= { LectorServiceImpl.class, RequestFromLectorServiceImpl.class, GroupServiceImpl.class
                          , DaoLectorImpl.class, DaoRequestFromLectorImpl.class, DaoGroupImpl.class})
@ComponentScan("com.epam.brest.*")
@EntityScan("com.epam.brest")
@Transactional()
public class LectorServiceImplTestIT {

    private final Logger logger = LogManager.getLogger(LectorServiceImplTestIT.class);

    @Autowired
    private LectorServiceApi lectorService;


    @Autowired
    private RequestFromLectorServiceApi requestFromLectorService;


    @Autowired
    private GroupServiceApi groupService;


    @BeforeEach
    public void setUp() {
        String[] groupes = new String[]{"e1", "e2", "e3", "e4", "e5", "e6"};
        List<Group> grup = Arrays.stream(groupes)
                .map(gr -> groupService.createNewGroupService(gr))
                .collect(Collectors.toList());
        Lector user = lectorService.createNewLectorService(new Lector("TOMMY", "tom", "1111", "iuy@aa.com"));
    }


    @Test
    public void isNewUserServiceImpl() {
        logger.info("GET ALL USERS {}");
        //User user = userService.saveNewUserService(new User ("TOMMY", "tom", "1111", "iuy@aa.com"));
        List<Lector> users = (List<Lector>) lectorService.getAllLectorsService();
        logger.info("GET ALL USERS {} SIZE = " + users.size());
        assertTrue(users.size() == 1);
        assertTrue(users.get(users.size() - 1).getNameLector().equals("TOMMY"));
        Lector user = users.get(users.size() - 1);
        List<RequestFromLector> requests = requestFromLectorService.getAllRequestsFromLectorService(user.getIdLector());
        assertTrue(requests.size() == 6);

    }

    @Test
    public void isGetUserByNameServiceImpl() {
        logger.info("GET USER BY Name {}");
        Lector user = (Lector) lectorService.getLectorByLectorsNameService("TOMMY");
        assertTrue(user.getNameLector().equals("TOMMY"));
        List<RequestFromLector> requests = requestFromLectorService.getAllRequestsFromLectorService(user.getIdLector());
        assertTrue(requests.size() == 6);

    }

    @Test
    public void isGetUserByEmailServiceAndIdImpl() {
        logger.info("GET USER BY Email {}");
        Lector user = (Lector) lectorService.getLectorByEmailService("iuy@aa.com");
        assertTrue(user.getEmailLector().equals("iuy@aa.com"));
        List<RequestFromLector> requests = requestFromLectorService.getAllRequestsFromLectorService(user.getIdLector());
        assertTrue(requests.size() == 6);
        user = (Lector) lectorService.getLectorByEmailService("qqiuy@aa.com");
        assertFalse(user.getEmailLector().equals("qqiuy@aa.com"));
        Lector user2 = lectorService.getLectorByLectorsNameService("TOMMY");
        Lector users3 = (Lector) lectorService.getLectorByIdLectorService(user2.getIdLector());
        assertTrue(users3.getNameLector().equals("TOMMY"));
    }

    @Test
    public void isCreateUserService() {
       logger.info("CREATE NEW USER {}");
       Lector user = lectorService.createNewLectorService(new Lector("Mike", "mike", "1111", "isocrol@aa.com"));
       assertTrue(user.getEmailLector().equals("isocrol@aa.com"));
       assertTrue(user.getNameLector().equals("Mike"));
       Lector user1 = lectorService.getLectorByLectorsNameService("Mike");
       assertTrue(user1.getNameLector().equals("Mike") && user1.getIdLector() == user.getIdLector());
       List<RequestFromLector> requests = requestFromLectorService.getAllRequestsFromLectorService(user.getIdLector());
       assertTrue(requests.size() == 6);
    }


    @Test
    public void isUpdateUserService() {
        logger.info("UPDATE USER {}");
        Lector user = lectorService.createNewLectorService(new Lector("Mike", "mike", "1111", "isocrol@aa.com"));
        assertTrue(user.getEmailLector().equals("isocrol@aa.com"));
        assertTrue(user.getNameLector().equals("Mike"));
        Lector user1 = lectorService.getLectorByLectorsNameService("Mike");
        assertTrue(user1.getNameLector().equals("Mike") && user1.getIdLector() == user.getIdLector());
        user.setNameLector("Kim");
        user =  lectorService.updateLectorService(user);
        assertTrue(user.getNameLector().equals("Kim"));
        user1 = lectorService.getLectorByLectorsNameService("Kim");
        assertTrue(user1.getNameLector().equals("Kim") && user1.getIdLector() == user.getIdLector());
    }

    @Test
    public void isDeleteUserService() {
        logger.info("DELETE USER {}");
        Lector user = lectorService.createNewLectorService(new Lector("Mike", "mike", "1111", "isocrol@aa.com"));
        assertTrue(user.getNameLector().equals("Mike"));
        assertTrue(lectorService.getAllLectorsService().size() == 2);
        lectorService.deleteLectorService(user);
        assertTrue(lectorService.getAllLectorsService().size() == 1);

        user = lectorService.createNewLectorService(new Lector("Mike", "mike", "1111", "isocrol@aa.com"));
        assertTrue(lectorService.getAllLectorsService().size() == 2);
        lectorService.deleteLectorByIdLectorService(user.getIdLector());
        assertTrue(lectorService.getAllLectorsService().size() == 1);
    }

    @Test
    public void isGetUserByIdService() {
        logger.info("GET USER BY ID{}");
        Lector user = lectorService.createNewLectorService(new Lector("Mike", "mike", "1111", "isocrol@aa.com"));
        assertTrue(user.getNameLector().equals("Mike"));
        assertTrue(lectorService.getAllLectorsService().size() == 2);
        Lector user1 = lectorService.getLectorByIdLectorService(user.getIdLector());
        assertTrue(user1.getNameLector().equals("Mike"));
        assertTrue(user1.getNameLector().equals(user.getNameLector()));
    }

}
