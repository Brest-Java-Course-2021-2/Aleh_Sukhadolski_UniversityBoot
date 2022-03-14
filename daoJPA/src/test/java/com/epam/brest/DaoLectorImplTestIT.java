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
@SpringBootTest (classes= { DaoLectorImpl.class, DaoRequestFromLectorImpl.class, DaoGroupImpl.class})
//@ContextConfiguration
@ComponentScan("com.epam.brest")
@EntityScan("com.epam.brest")
@Transactional()
public class DaoLectorImplTestIT {

    private final Logger logger = LogManager.getLogger(DaoLectorImplTestIT.class);

    @Autowired
    private DaoLectorApi daoLector;

    @Autowired
    private DaoRequestFromLectorApi daoRequestFromLector;

    @Test
    public void testGetAll() {
        logger.info("GET ALL USERS {}");
        daoLector.saveOrUpdateLector(new Lector("Monya", "monya", "1111", "email@mail.com"));
        List<Lector> users = (List<Lector>) daoLector.getAllLectors();
        assertTrue(users.size() == 1);
        assertTrue(users.get(0).getNameLector().equals("Monya"));
    }


    @Test
    public void testSaveAndGet() {
        logger.info("SAVE USER {}");
        daoLector.saveOrUpdateLector(new Lector("Monya", "monya", "1111", "email@mail.com"));
        logger.info("USER SAVED SUCCESS{}");
        logger.info("FIND USER BY NAME {}");
        Lector user = (Lector) daoLector.getLectorByName("Monya");
        assertTrue(user.getNameLector().equals("Monya"));
        user = (Lector) daoLector.getLectorByEmail("email@mail.com");
        assertTrue(user.getEmailLector().equals("email@mail.com"));
        logger.info("FOUND USER BY NAME SUCCESS{}");
    }

    @Test
    public void testSaveAndUpdate() {
        logger.info("SAVE USER {}");
        daoLector.saveOrUpdateLector(new Lector("Monya", "monya", "1111", "email@mail.com"));
        Lector user = (Lector) daoLector.getLectorByName("Monya");
        assertTrue(user.getNameLector().equals("Monya"));
        logger.info("USER SAVED AND READ BY NAME SUCCESS{}");
        Lector userUpdated = user;
        assertTrue(userUpdated.getNameLector().equals("Monya") && userUpdated.getLoginLector().equals("monya"));
        userUpdated.setNameLector("Tony");
        logger.info("USER UPDATE {}" + userUpdated);
        daoLector.saveOrUpdateLector(userUpdated);
        Lector userNew = daoLector.getLectorById(userUpdated.getIdLector());
        assertTrue(userNew.getNameLector().equals("Tony") && userNew.getLoginLector().equals("monya"));
        logger.info("USER UPDATED AND READ SUCCESS{}");
    }


    @Test
    public void testDeleteByUser() {
        logger.info("DELETE USER BY USER{}");
        daoLector.saveOrUpdateLector(new Lector("Monya", "monya", "1111", "email@mail.com"));
        List<Lector> users = (List<Lector>) daoLector.getAllLectors();
        assertTrue(users.size() == 1);
        Lector user = users.get(0);
        daoRequestFromLector.deleteAllRequestsFromLector(user.getIdLector());
        daoLector.deleteLector(user);
        user = (Lector) daoLector.getLectorByName(user.getNameLector());
        assertTrue(user.getIdLector() == 0);
        user = (Lector) daoLector.getLectorByEmail(user.getEmailLector());
        assertTrue(user.getIdLector() == 0);
        logger.info("DELETE USER BY USER SUCCESS {}");
    }


    @Test
    public void testDeleteById() {
        logger.info("DELETE USER BY ID{}");
        daoLector.saveOrUpdateLector(new Lector("Monya", "monya", "1111", "email@mail.com"));
        List<Lector> users = (List<Lector>) daoLector.getAllLectors();
        assertTrue(users.size() == 1);
        Lector user = users.get(0);
        daoRequestFromLector.deleteAllRequestsFromLector(user.getIdLector());
        daoLector.deleteLectorById(user.getIdLector());
        user = (Lector) daoLector.getLectorByName(user.getNameLector());
        assertTrue(user.getIdLector() == 0);
        user = (Lector) daoLector.getLectorByEmail(user.getEmailLector());
        assertTrue(user.getIdLector() == 0);
        logger.info("DELETE USER BY USER SUCCESS {}");

    }


}
