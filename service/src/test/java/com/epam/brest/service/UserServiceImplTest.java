package com.epam.brest.service;


import com.epam.brest.dao.UserDao;
import com.epam.brest.daoAPI.DaoGroupeApi;
import com.epam.brest.daoAPI.DaoRequestApi;
import com.epam.brest.daoAPI.DaoUserApi;
import com.epam.brest.model.User;

import com.epam.brest.serviceapi.GroupeServiceApi;
import com.epam.brest.serviceapi.RequestServiceApi;
import com.epam.brest.serviceapi.UserServiceApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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



    @Test
    public void isNewUserServiceImpl(){
        logger.info("GET ALL USERS {}");
        User user = userService.saveNewUserService(new User ("TOMMY", "tom", "1111", "iuy@aa.com"));
        List <User> users = (List<User>) userService.getAllUsersService();
        logger.info("GET ALL USERS {} SIZE = " + users.size());
        assertTrue(users.size() == 1);
        assertTrue(users.get(users.size()-1).getName().equals("TOMMY"));

    }

}
