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

    public List<Lector> getAllUsers() {
        logger.info("GET ALL USERS {}");
        return (List<Lector>) userRepository.findAllUsers();
    }


    public Lector getUserByName(String name) {
        logger.info("GET USER BY NAME{} " + name);
        return userRepository.findUserByName(name);
    }

    public Lector getUserByEmail(String email) {
        logger.info("GET USER BY EMAIL{} " + email);
        return userRepository.findUserByEmail(email);
    }

    public Lector getUserById(Integer id) {
        logger.info("GET USER BY ID{} " + id);
        return (Lector) userRepository.findUserById(id);
    }

    public Lector saveAndUpdateUser(Lector user) {
        logger.info("SAVE USER {} " + user);
        return (Lector) userRepository.saveAndFlushUser(user);
    }

    public void deleteUser(Lector user) {
        logger.info("DELETE USER {}" + user);
        userRepository.deleteUserById(user.getIdLector());
    }

    public void deleteUserById(Integer id) {
        logger.info("DELETE USER id = " + id );
        userRepository.deleteUserById(id);
    }


}
