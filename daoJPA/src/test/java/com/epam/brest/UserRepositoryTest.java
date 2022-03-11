package com.epam.brest;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import javax.transaction.Transactional;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootApplication
@SpringBootTest (classes= { UserDao.class, RequestDao.class, GroupeDao.class})
//@ContextConfiguration
@ComponentScan("com.epam.brest.*")
@EntityScan("com.epam.brest")
@Transactional()
public class UserRepositoryTest {

    private final Logger logger = LogManager.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserJpaRepository userRepository;


    @Autowired
    private DaoUserApi userDao;

    @Autowired
    private DaoRequestApi requestDao;

    @Test
    public void testGetAll() {
        logger.info("GET ALL USERS {}");
        userDao.saveAndUpdateUser(new User("Monya", "monya", "1111", "email@mail.com"));
        List<User> users = (List<User>) userDao.getAllUsers();
        assertTrue(users.size() == 1);
        assertTrue(users.get(0).getName().equals("Monya"));
    }


    @Test
    public void testSaveAndGet() {
        logger.info("SAVE USER {}");
        userDao.saveAndUpdateUser(new User("Monya", "monya", "1111", "email@mail.com"));
        logger.info("USER SAVED SUCCESS{}");
        logger.info("FIND USER BY NAME {}");
        User user = (User) userDao.getUserByName("Monya");
        assertTrue(user.getName().equals("Monya"));
        user = (User) userDao.getUserByEmail("email@mail.com");
        assertTrue(user.getEmail().equals("email@mail.com"));
        logger.info("FOUND USER BY NAME SUCCESS{}");
    }

    @Test
    public void testSaveAndUpdate() {
        logger.info("SAVE USER {}");
        userDao.saveAndUpdateUser(new User("Monya", "monya", "1111", "email@mail.com"));
        User user = (User) userDao.getUserByName("Monya");
        assertTrue(user.getName().equals("Monya"));
        logger.info("USER SAVED AND READ BY NAME SUCCESS{}");
        User userUpdated = user;
        assertTrue(userUpdated.getName().equals("Monya") && userUpdated.getLogin().equals("monya"));
        userUpdated.setName("Tony");
        logger.info("USER UPDATE {}" + userUpdated);
        userDao.saveAndUpdateUser(userUpdated);
        User userNew = userDao.getUserById(userUpdated.getId());
        assertTrue(userNew.getName().equals("Tony") && userNew.getLogin().equals("monya"));
        logger.info("USER UPDATED AND READ SUCCESS{}");
    }


    @Test
    public void testDeleteByUser() {
        logger.info("DELETE USER BY USER{}");
        userDao.saveAndUpdateUser(new User("Monya", "monya", "1111", "email@mail.com"));
        List<User> users = (List<User>) userDao.getAllUsers();
        assertTrue(users.size() == 1);
        User user = users.get(0);
        requestDao.deleteAllRequestsOfUser(user.getId());
        userDao.deleteUser(user);
        user = (User) userDao.getUserByName(user.getName());
        assertTrue(user.getId() == 0);
        user = (User) userDao.getUserByEmail(user.getEmail());
        assertTrue(user.getId() == 0);
        logger.info("DELETE USER BY USER SUCCESS {}");
    }


    @Test
    public void testDeleteById() {
        logger.info("DELETE USER BY ID{}");
        userDao.saveAndUpdateUser(new User("Monya", "monya", "1111", "email@mail.com"));
        List<User> users = (List<User>) userDao.getAllUsers();
        assertTrue(users.size() == 1);
        User user = users.get(0);
        requestDao.deleteAllRequestsOfUser(user.getId());
        userDao.deleteUserById(user.getId());
        user = (User) userDao.getUserByName(user.getName());
        assertTrue(user.getId() == 0);
        user = (User) userDao.getUserByEmail(user.getEmail());
        assertTrue(user.getId() == 0);
        logger.info("DELETE USER BY USER SUCCESS {}");

    }


}
