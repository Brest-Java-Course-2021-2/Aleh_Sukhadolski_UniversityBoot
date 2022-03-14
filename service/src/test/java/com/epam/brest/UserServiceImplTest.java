package com.epam.brest;

import com.epam.brest.serviceapi.GroupeServiceApi;
import com.epam.brest.serviceapi.RequestServiceApi;
import com.epam.brest.serviceapi.UserServiceApi;


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
@SpringBootTest (classes= { UserServiceImpl.class, RequestServiceImpl.class, GroupeServiceImpl.class
                          , DaoLectorImpl.class, DaoRequestFromLectorImpl.class, DaoGroupImpl.class})
@ComponentScan("com.epam.brest.*")
@EntityScan("com.epam.brest")
@Transactional()
public class UserServiceImplTest {

    private final Logger logger = LogManager.getLogger(UserServiceImplTest.class);

    @Autowired
    private UserServiceApi userService;


    @Autowired
    private RequestServiceApi requestService;


    @Autowired
    private GroupeServiceApi groupeService;


    @BeforeEach
    public void setUp() {
        String[] groupes = new String[]{"e1", "e2", "e3", "e4", "e5", "e6"};
        List<Group> grup = Arrays.stream(groupes)
                .map(gr -> groupeService.insertNewGroupeService(gr))
                .collect(Collectors.toList());
        Lector user = userService.saveNewUserService(new Lector("TOMMY", "tom", "1111", "iuy@aa.com"));
    }


    @Test
    public void isNewUserServiceImpl() {
        logger.info("GET ALL USERS {}");
        //User user = userService.saveNewUserService(new User ("TOMMY", "tom", "1111", "iuy@aa.com"));
        List<Lector> users = (List<Lector>) userService.getAllUsersService();
        logger.info("GET ALL USERS {} SIZE = " + users.size());
        assertTrue(users.size() == 1);
        assertTrue(users.get(users.size() - 1).getNameLector().equals("TOMMY"));
        Lector user = users.get(users.size() - 1);
        List<RequestFromLector> requests = requestService.getAllRequestsService(user.getIdLector());
        assertTrue(requests.size() == 6);

    }

    @Test
    public void isGetUserByNameServiceImpl() {
        logger.info("GET USER BY Name {}");
        Lector user = (Lector) userService.getUserByNameService("TOMMY");
        assertTrue(user.getNameLector().equals("TOMMY"));
        List<RequestFromLector> requests = requestService.getAllRequestsService(user.getIdLector());
        assertTrue(requests.size() == 6);

    }

    @Test
    public void isGetUserByEmailServiceAndIdImpl() {
        logger.info("GET USER BY Email {}");
        Lector user = (Lector) userService.getUserByEmailService("iuy@aa.com");
        assertTrue(user.getEmailLector().equals("iuy@aa.com"));
        List<RequestFromLector> requests = requestService.getAllRequestsService(user.getIdLector());
        assertTrue(requests.size() == 6);
        user = (Lector) userService.getUserByEmailService("qqiuy@aa.com");
        assertFalse(user.getEmailLector().equals("qqiuy@aa.com"));
        Lector user2 = userService.getUserByNameService("TOMMY");
        Lector users3 = (Lector) userService.getUserByIdService(user2.getIdLector());
        assertTrue(users3.getNameLector().equals("TOMMY"));
    }

    @Test
    public void isCreateUserService() {
       logger.info("CREATE NEW USER {}");
       Lector user = userService.saveNewUserService(new Lector("Mike", "mike", "1111", "isocrol@aa.com"));
       assertTrue(user.getEmailLector().equals("isocrol@aa.com"));
       assertTrue(user.getNameLector().equals("Mike"));
       Lector user1 = userService.getUserByNameService("Mike");
       assertTrue(user1.getNameLector().equals("Mike") && user1.getIdLector() == user.getIdLector());
       List<RequestFromLector> requests = requestService.getAllRequestsService(user.getIdLector());
       assertTrue(requests.size() == 6);
    }


    @Test
    public void isUpdateUserService() {
        logger.info("UPDATE USER {}");
        Lector user = userService.saveNewUserService(new Lector("Mike", "mike", "1111", "isocrol@aa.com"));
        assertTrue(user.getEmailLector().equals("isocrol@aa.com"));
        assertTrue(user.getNameLector().equals("Mike"));
        Lector user1 = userService.getUserByNameService("Mike");
        assertTrue(user1.getNameLector().equals("Mike") && user1.getIdLector() == user.getIdLector());
        user.setNameLector("Kim");
        user =  userService.updateUserService(user);
        assertTrue(user.getNameLector().equals("Kim"));
        user1 = userService.getUserByNameService("Kim");
        assertTrue(user1.getNameLector().equals("Kim") && user1.getIdLector() == user.getIdLector());
    }

    @Test
    public void isDeleteUserService() {
        logger.info("DELETE USER {}");
        Lector user = userService.saveNewUserService(new Lector("Mike", "mike", "1111", "isocrol@aa.com"));
        assertTrue(user.getNameLector().equals("Mike"));
        assertTrue(userService.getAllUsersService().size() == 2);
        userService.deleteUserService(user);
        assertTrue(userService.getAllUsersService().size() == 1);

        user = userService.saveNewUserService(new Lector("Mike", "mike", "1111", "isocrol@aa.com"));
        assertTrue(userService.getAllUsersService().size() == 2);
        userService.deleteUserByIdService(user.getIdLector());
        assertTrue(userService.getAllUsersService().size() == 1);
    }

    @Test
    public void isGetUserByIdService() {
        logger.info("GET USER BY ID{}");
        Lector user = userService.saveNewUserService(new Lector("Mike", "mike", "1111", "isocrol@aa.com"));
        assertTrue(user.getNameLector().equals("Mike"));
        assertTrue(userService.getAllUsersService().size() == 2);
        Lector user1 = userService.getUserByIdService(user.getIdLector());
        assertTrue(user1.getNameLector().equals("Mike"));
        assertTrue(user1.getNameLector().equals(user.getNameLector()));
    }

}
