package com.epam.brest.service;

import com.epam.brest.model.Groupe;
import com.epam.brest.model.Request;
import com.epam.brest.model.User;

import com.epam.brest.serviceapi.GroupeServiceApi;
import com.epam.brest.serviceapi.RequestServiceApi;
import com.epam.brest.serviceapi.UserServiceApi;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ComponentScan("com.epam.brest.*")
@EntityScan("com.epam.brest.model")
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
        List<Groupe> grup = Arrays.stream(groupes)
                .map(gr -> groupeService.insertNewGroupeService(gr))
                .collect(Collectors.toList());
        User user = userService.saveNewUserService(new User("TOMMY", "tom", "1111", "iuy@aa.com"));
    }


    @Test
    public void isNewUserServiceImpl() {
        logger.info("GET ALL USERS {}");
        //User user = userService.saveNewUserService(new User ("TOMMY", "tom", "1111", "iuy@aa.com"));
        List<User> users = (List<User>) userService.getAllUsersService();
        logger.info("GET ALL USERS {} SIZE = " + users.size());
        assertTrue(users.size() == 1);
        assertTrue(users.get(users.size() - 1).getName().equals("TOMMY"));
        User user = users.get(users.size() - 1);
        List<Request> requests = requestService.getAllRequestsService(user.getId());
        assertTrue(requests.size() == 6);

    }

    @Test
    public void isGetUserByNameServiceImpl() {
        logger.info("GET USER BY Name {}");
        List<User> users = (List<User>) userService.getUserByNameService("TOMMY");
        assertTrue(users.size() == 1);
        assertTrue(users.get(users.size() - 1).getName().equals("TOMMY"));
        User user = users.get(users.size() - 1);
        List<Request> requests = requestService.getAllRequestsService(user.getId());
        assertTrue(requests.size() == 6);

    }

    @Test
    public void isGetUserByEmailServiceAndIdImpl() {
        logger.info("GET USER BY Email {}");
        List<User> users = (List<User>) userService.getUserByEmailService("iuy@aa.com");
        assertTrue(users.size() == 1);
        assertTrue(users.get(users.size() - 1).getEmail().equals("iuy@aa.com"));
        User user = users.get(users.size() - 1);
        List<Request> requests = requestService.getAllRequestsService(user.getId());
        assertTrue(requests.size() == 6);
        List<User> users1 = (List<User>) userService.getUserByEmailService("qqiuy@aa.com");
        assertFalse(users1.size() == 1);
        if (!users1.isEmpty()) {
            assertFalse(users.get(users1.size() - 1).getEmail().equals("qqiuy@aa.com"));
        }
        List<User> users2 = userService.getUserByNameService("TOMMY");
        User users3 = (User) userService.getUserByIdService(users2.get(0).getId());
        assertTrue(users3.getName().equals("TOMMY"));
    }

    @Test
    public void isCreateUserService() {
       logger.info("CREATE NEW USER {}");
       User user = userService.saveNewUserService(new User("Mike", "mike", "1111", "isocrol@aa.com"));
       assertTrue(user.getEmail().equals("isocrol@aa.com"));
       assertTrue(user.getName().equals("Mike"));
       List<User> users = userService.getUserByNameService("Mike");
        if (!users.isEmpty()) {
            assertTrue(users.get(users.size() - 1).getName().equals("Mike")
                                            && users.get(users.size() - 1).getId() == user.getId());
        }
    }


    @Test
    public void isUpdateUserService() {
        logger.info("UPDATE USER {}");
        User user = userService.saveNewUserService(new User("Mike", "mike", "1111", "isocrol@aa.com"));
        assertTrue(user.getEmail().equals("isocrol@aa.com"));
        assertTrue(user.getName().equals("Mike"));
        List<User> users = userService.getUserByNameService("Mike");
        if (!users.isEmpty()) {
            assertTrue(users.get(users.size() - 1).getName().equals("Mike")
                    && users.get(users.size() - 1).getId() == user.getId());
        }

        user.setName("Kim");
        user =  userService.updateUserService(user);
        assertTrue(user.getName().equals("Kim"));

        users = userService.getUserByNameService("Kim");
        if (!users.isEmpty()) {
            assertTrue(users.get(users.size() - 1).getName().equals("Kim")
                    && users.get(users.size() - 1).getId() == user.getId());
        }
    }

    @Test
    public void isDeleteUserService() {
        logger.info("DELETE USER {}");
        User user = userService.saveNewUserService(new User("Mike", "mike", "1111", "isocrol@aa.com"));
        assertTrue(user.getName().equals("Mike"));
        assertTrue(userService.getAllUsersService().size() == 2);
        userService.deleteUserService(user);
        assertTrue(userService.getAllUsersService().size() == 1);

        user = userService.saveNewUserService(new User("Mike", "mike", "1111", "isocrol@aa.com"));
        assertTrue(userService.getAllUsersService().size() == 2);
        userService.deleteUserByIdService(user.getId());
        assertTrue(userService.getAllUsersService().size() == 1);
    }

    @Test
    public void isGetUserByIdService() {
        logger.info("GET USER BY ID{}");
        User user = userService.saveNewUserService(new User("Mike", "mike", "1111", "isocrol@aa.com"));
        assertTrue(user.getName().equals("Mike"));
        assertTrue(userService.getAllUsersService().size() == 2);
        User user1 = userService.getUserByIdService(user.getId());
        assertTrue(user1.getName().equals("Mike"));
        assertTrue(user1.getName().equals(user.getName()));
    }

}
