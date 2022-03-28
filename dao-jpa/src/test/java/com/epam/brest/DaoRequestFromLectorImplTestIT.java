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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootApplication
@SpringBootTest (classes= { DaoLectorImpl.class, DaoRequestFromLectorImpl.class, DaoGroupImpl.class})
@ComponentScan("com.epam.brest")
@EntityScan("com.epam.brest")
@Transactional()
public class DaoRequestFromLectorImplTestIT {

    private final Logger logger = LogManager.getLogger(DaoRequestFromLectorImplTestIT.class);

    @Autowired
    private DaoRequestFromLectorApi daoRequestFromLector;

    @Autowired
    private DaoLectorApi daoLector;

    @Autowired
    private DaoGroupApi daoGroup;

    @BeforeEach
    public void setUp() {
        List <String> groups = Arrays.asList(new String[]{"e1", "e2", "e3", "e4", "e5"});
        groups.stream().peek(group -> daoGroup.insertNewGroup(group));
        Lector lector = new Lector("JoeFrasier", "joe", "1111", "mail@mail.com");
        daoLector.saveOrUpdateLector(lector);
        lector = daoLector.getLectorByName("JoeFrasier");
        daoRequestFromLector.createEmptyRequestsForNewLector(lector.getIdLector(), groups);
        lector = new Lector("MikeTyson", "mike", "2222", "mike@tyson.com");
        daoLector.saveOrUpdateLector(lector);
        lector = daoLector.getLectorByName("MikeTyson");
        daoRequestFromLector.createEmptyRequestsForNewLector(lector.getIdLector(), groups);
        lector = new Lector("SonnyListon", "sonny", "3333", "sonny@liston.com");
        daoLector.saveOrUpdateLector(lector);
        lector = daoLector.getLectorByName("SonnyListon");
        daoRequestFromLector.createEmptyRequestsForNewLector(lector.getIdLector(), groups);
    }


    @Test
    public void getRequestsByIdlector(){
        logger.info("Get requests from lector by IdLector");
        Lector lector = daoLector.getLectorByName("MikeTyson");
        List<RequestFromLector> requestsFromLector = daoRequestFromLector.getAllRequestsFromLectorByIdLector(lector.getIdLector());
        logger.info("Get requests from lector by IdLector -> " + requestsFromLector.size() + " id= " + lector.getNameLector());
        assertTrue(requestsFromLector.size() == 5);
    }



}
