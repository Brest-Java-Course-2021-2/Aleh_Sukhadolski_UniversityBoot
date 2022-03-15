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


    @Test
    public void testRequestsForNewLectorAndNewGroup() {
        logger.info("Create new lector {}");
        Lector lector = new Lector("Joe Frasier", "joe", "1111", "mail@mail.com");
        daoLector.saveOrUpdateLector(lector);
        lector = daoLector.getLectorByName("Joe Frasier");
        logger.info("Requests for new Lector {}" + lector);
        List <String> groups = Arrays.asList(new String[]{"e1", "e2", "e3", "e4", "e5"});
        daoRequestFromLector.createEmptyRequestsForNewLector(lector.getIdLector(),groups);

        List<RequestFromLector> requestsFromLector = daoRequestFromLector
                                                     .getAllRequestsFromLectorByIdLector(lector.getIdLector());

        assertTrue(requestsFromLector.size() == 5);
        List<Integer> lectorsId = (List<Integer>) daoLector.getAllLectors()
                                                    .stream()
                                                    .flatMap(us -> Stream.of(us.getIdLector()))
                                                    .collect(Collectors.toList());

        daoRequestFromLector.createRequestsForLectorsWhenCreateNewGroup("e6", lectorsId);
        requestsFromLector = daoRequestFromLector.getAllRequestsFromLectorByIdLector(lector.getIdLector());
        assertTrue(requestsFromLector.size() == 6);

        List<Lector> lectors = daoLector.getAllLectors();
        for (Lector lect : lectors){
            requestsFromLector = daoRequestFromLector.getAllRequestsFromLectorByIdLector(lect.getIdLector());
            assertTrue(requestsFromLector.size() == 6);
        }
    }

    @Test
    public void testCreateAndDeleteRequestFromLector() {
        logger.info("Create new lector {} + nameLector = Joe Frasier");
        Lector lector = new Lector("Joe Frasier", "joe", "1111", "mail@mail.com");
        daoLector.saveOrUpdateLector(lector);
        lector = daoLector.getLectorByName("Joe Frasier");
        logger.info("New lector created {} name = " + lector.getNameLector());
        logger.info("Create request for new Lector {}" + lector);
        List <String> groups = Arrays.asList(new String[]{"e1", "e2", "e3", "e4", "e5"});
        lector = daoLector.getLectorByName("Joe Frasier");
        List<RequestFromLector> requestsFromLectors = (List<RequestFromLector>) daoRequestFromLector
                                                      .createEmptyRequestsForNewLector(lector.getIdLector(), groups);

        RequestFromLector requestFromLector = daoRequestFromLector
                                                       .getAllRequestsFromLectorByIdLector(lector.getIdLector()).get(0);

        requestFromLector.setNumberOfPairs("2");
        requestFromLector.setSubjectOfLector("fizo");
        daoRequestFromLector.updateRequestFromLector(requestFromLector);
        requestFromLector = daoRequestFromLector.getAllRequestsFromLectorByIdLector(lector.getIdLector()).get(0);

        assertTrue(requestFromLector.getNumberOfPairs().equals("2")
                           && requestFromLector.getSubjectOfLector().equals("fizo"));

        logger.info("Flush request {}" + requestFromLector);
        daoRequestFromLector.flushRequestForLector(requestFromLector);
        requestFromLector = daoRequestFromLector.getAllRequestsFromLectorByIdLector(lector.getIdLector()).get(0);

        assertTrue(requestFromLector.getNumberOfPairs().equals("0")
                            && requestFromLector.getSubjectOfLector().equals("0000"));

    }

    @Test
    public void testDeleteAllRequestforLector() {
        logger.info("Create new lector {} + name = Joe Frasier");
        Lector lector = new Lector("Joe Frasier", "joe", "1111", "mail@mail.com");
        daoLector.saveOrUpdateLector(lector);
        lector = daoLector.getLectorByName("Joe Frasier");
        logger.info("New lector created {} name = " + lector.getNameLector());
        logger.info("Create request for new Lector {}" + lector);
        List <String> groups = Arrays.asList(new String[]{"e1", "e2", "e3", "e4", "e5"});
        lector = daoLector.getLectorByName("Joe Frasier");
        List<RequestFromLector> requestsFromLector = (List<RequestFromLector>) daoRequestFromLector
                                           .createEmptyRequestsForNewLector(lector.getIdLector(), groups);

        List<RequestFromLector> requestsFromLector1 = (List<RequestFromLector>) requestsFromLector.stream()
                .map(request  -> {request.setNumberOfPairs("3");
                                  return request;
                                  }).collect(Collectors.toList());

        requestsFromLector = daoRequestFromLector.updateAllRequestsForLector(requestsFromLector1);
        boolean ifChanged = true;
        for (RequestFromLector req : requestsFromLector){
            ifChanged = ifChanged && (req.getNumberOfPairs().equals("3"));
        }

        logger.info("Created all requests for new Lector {}" + ifChanged);
        assertTrue(ifChanged);

        logger.info("Flush all requests for new Lector {}" + lector);
        daoRequestFromLector.deleteAllRequestsFromLector(lector.getIdLector());
        requestsFromLector = daoRequestFromLector.getAllRequestsFromLectorByIdLector(lector.getIdLector());
        assertTrue(requestsFromLector.size() == 0);

    }

}
