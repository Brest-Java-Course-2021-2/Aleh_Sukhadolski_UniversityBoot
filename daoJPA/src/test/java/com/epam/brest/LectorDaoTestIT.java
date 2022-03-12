package com.epam.brest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootApplication
@SpringBootTest(classes= {LectorDao.class})
//@ContextConfiguration
@ComponentScan("com.epam.brest")
@EntityScan("com.epam.brest")
@Transactional()
public class LectorDaoTestIT {

    private final Logger logger = LogManager.getLogger(LectorDaoTestIT.class);

    @Autowired
    private DaoLectorApi daoLector;

    @BeforeEach
    public void setUp() {
        logger.info("Initialize Lectors for test {}");
        daoLector.saveAndFlushLector(
            new Lector("MikeTyson", "mike", "1111", "mike@mike.com"));
        daoLector.saveAndFlushLector(
                new Lector("JohnBonjovi", "john", "2222", "john@john.com"));
    }


    @Test
    public void isGetAllLectors() {
        logger.info("Get all lectors {}");
        List <Lector> lectors = daoLector.findAllLectors();
        assertTrue(lectors.size() == 2);
    }

    @Test
    public void isGetLectorName() {
        logger.info("Get lector by name {}");
        Lector lector = daoLector.findLectorByNameLector("MikeTyson");
        assertTrue(lector.getNameLector().equals("MikeTyson"));
        lector = daoLector.findLectorByNameLector("Tony");
        assertFalse(lector.getNameLector().equals("Tony"));
    }

    @Test
    public void isGetLectorEmail() {
        logger.info("Get lector by email {}");
        Lector lector = daoLector.findLectorByEmailLector("mike@mike.com");
        assertTrue(lector.getEmailLector().equals("mike@mike.com"));
        lector = daoLector.findLectorByEmailLector("tony@tony.com");
        assertFalse(lector.getEmailLector().equals("tony@tony.com"));
    }

}
