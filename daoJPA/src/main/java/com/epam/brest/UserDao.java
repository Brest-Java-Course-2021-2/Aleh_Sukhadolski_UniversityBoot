package com.epam.brest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@ComponentScan("com.epam.brest")
public class UserDao implements DaoUserApi {

    private final Logger logger = LogManager.getLogger(UserDao.class);

    @Autowired
    private UserJpaRepository userRepository;

    public List<User> getAllUsers() {
        logger.info("GET ALL USERS {}");
        return (List<User>) userRepository.findAllUsers();
    }


    public User getUserByName(String name) {
        logger.info("GET USER BY NAME{} " + name);
        return userRepository.findUserByName(name);
    }

    public User getUserByEmail(String email) {
        logger.info("GET USER BY EMAIL{} " + email);
        return userRepository.findUserByEmail(email);
    }

    public User getUserById(Integer id) {
        logger.info("GET USER BY ID{} " + id);
        return (User) userRepository.findUserById(id);
    }

    public User saveAndUpdateUser(User user) {
        logger.info("SAVE USER {} " + user);
        return (User) userRepository.saveAndFlushUser(user);
    }

    public void deleteUser(User user) {
        logger.info("DELETE USER {}" + user);
        userRepository.deleteUserById(user.getId());
    }

    public void deleteUserById(Integer id) {
        logger.info("DELETE USER id = " + id );
        userRepository.deleteUserById(id);
    }


}
