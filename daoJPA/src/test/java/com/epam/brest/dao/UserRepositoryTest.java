package com.epam.brest.dao;


import com.epam.brest.daoAPI.DaoRequestApi;
import com.epam.brest.daoAPI.DaoUserApi;
import com.epam.brest.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import javax.transaction.Transactional;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ComponentScan("com.epam.brest.*")
@EntityScan("com.epam.brest.model")
@Transactional()
public class UserRepositoryTest {

    private final Logger logger = LogManager.getLogger(UserRepositoryTest.class);

    @Autowired
    private DaoUserApi userDao;

    @Autowired
    private DaoRequestApi requestDao;

    @Test
    public void testGetAll() {
        logger.info("GET ALL USERS {}");
        List<User> users = (List<User>) userDao.getAllUsers();
        assertThat(users.size() > 0);
    }


    @Test
    public void testSaveAndGet() {
        logger.info("SAVE USER {}");
        userDao.saveAndUpdateUser(new User("Monya", "monya", "1111", "email@mail.com"));
        logger.info("USER SAVED SUCCESS{}");
        logger.info("FIND USER BY NAME {}");
        List<User> users = (List<User>) userDao.getUserByName("Monya");
        assertThat(users.size() == 1);
        logger.info("FOUND USER BY NAME SUCCESS{}");
        assertThat(users.get(0).getName().equals("Monya"));
    }

    @Test
    public void testSaveAndUpdate() {
        logger.info("SAVE USER {}");
        userDao.saveAndUpdateUser(new User("Monya", "monya", "1111", "email@mail.com"));
        List<User> users = (List<User>) userDao.getUserByName("Monya");
        assertThat(users.size() == 1);
        logger.info("USER SAVED AND READ BY NAME SUCCESS{}");
        User userUpdated = users.get(0);
        assertThat(userUpdated.getName().equals("Monya") && userUpdated.getLogin().equals("monya"));
        userUpdated.setName("Tony");
        logger.info("USER UPDATE {}" + userUpdated);
        userDao.saveAndUpdateUser(userUpdated);
        User userNew = userDao.getUserById(userUpdated.getId());
        assertThat(userNew.getName().equals("Tony") && userNew.getLogin().equals("monya"));
        logger.info("USER UPDATED AND READ SUCCESS{}");
    }


    @Test
    public void testDeleteByUser() {
        logger.info("DELETE USER BY USER{}");
        userDao.saveAndUpdateUser(new User("Monya", "monya", "1111", "email@mail.com"));
        List<User> users = (List<User>) userDao.getAllUsers();
        assertThat(users.size() > 0);
        User user = users.get(0);
        requestDao.deleteAllRequestsOfUser(user.getId());
        userDao.deleteUser(user);
        users = (List<User>) userDao.getUserByName(user.getName());
        assertThat(users.size() == 0);
        users = (List<User>) userDao.getUserByEmail(user.getEmail());
        assertThat(users.size() == 0);
        logger.info("DELETE USER BY USER SUCCESS {}");
    }


    @Test
    public void testDeleteById() {
        logger.info("DELETE USER BY ID{}");
        userDao.saveAndUpdateUser(new User("Monya", "monya", "1111", "email@mail.com"));
        List<User> users = (List<User>) userDao.getAllUsers();
        assertThat(users.size() > 0);
        User user = users.get(0);
        requestDao.deleteAllRequestsOfUser(user.getId());
        userDao.deleteUserById(user.getId());
        users = (List<User>) userDao.getUserByName(user.getName());
        assertThat(users.size() == 0);
        users = (List<User>) userDao.getUserByEmail(user.getEmail());
        assertThat(users.size() == 0);
        logger.info("DELETE USER BY USER SUCCESS {}");

    }


}
