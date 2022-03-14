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
//@ContextConfiguration
@ComponentScan("com.epam.brest")
@EntityScan("com.epam.brest")
@Transactional()
public class DaoRequestImplTestIT {

    private final Logger logger = LogManager.getLogger(DaoRequestImplTestIT.class);

    @Autowired
    private DaoRequestFromLectorApi daoRequestFromLector;

    @Autowired
    private DaoLectorApi daoLector;


    @Test
    public void testRequestsForNewUserAndNewGroupe() {
        logger.info("Create new user {}");
        Lector user = new Lector("Joe Frasier", "joe", "1111", "mail@mail.com");
        daoLector.saveOrUpdateLector(user);
        user = daoLector.getLectorByName("Joe Frasier");
        logger.info("Requests for new User {}" + user);
        List <String> groupes = Arrays.asList(new String[]{"e1", "e2", "e3", "e4", "e5"});
        daoRequestFromLector.createEmptyRequestsForNewLector(user.getIdLector(),groupes);
        List<RequestFromLector> requests = daoRequestFromLector.getAllRequestsFromLectorByIdLector(user.getIdLector());
        assertTrue(requests.size() == 5);
        List<Integer> usersId = (List<Integer>) daoLector.getAllLectors()
                                                    .stream()
                                                    .flatMap(us -> Stream.of(us.getIdLector()))
                                                    .collect(Collectors.toList());
        daoRequestFromLector.createRequestsForLectorsWhenCreateNewGroup("e6", usersId);
        requests = daoRequestFromLector.getAllRequestsFromLectorByIdLector(user.getIdLector());
        assertTrue(requests.size() == 6);

        List<Lector> users = daoLector.getAllLectors();
        for (Lector us : users){
            requests = daoRequestFromLector.getAllRequestsFromLectorByIdLector(us.getIdLector());
            assertTrue(requests.size() == 6);
        }
    }

    @Test
    public void testCreateAndDeleteRequest() {
        logger.info("Create new user {} + name = Joe Frasier");
        Lector user = new Lector("Joe Frasier", "joe", "1111", "mail@mail.com");
        daoLector.saveOrUpdateLector(user);
        user = daoLector.getLectorByName("Joe Frasier");
        logger.info("New user created {} name = " + user.getNameLector());
        logger.info("Create request for new User {}" + user);
        List <String> groupes = Arrays.asList(new String[]{"e1", "e2", "e3", "e4", "e5"});
        user = daoLector.getLectorByName("Joe Frasier");
        List<RequestFromLector> requests = (List<RequestFromLector>) daoRequestFromLector.createEmptyRequestsForNewLector(user.getIdLector(), groupes);
        RequestFromLector request = daoRequestFromLector.getAllRequestsFromLectorByIdLector(user.getIdLector()).get(0);
        request.setNumberOfPairs("2");
        request.setSubjectOfLector("fizo");
        daoRequestFromLector.updateRequestFromLector(request);
        request = daoRequestFromLector.getAllRequestsFromLectorByIdLector(user.getIdLector()).get(0);
        assertTrue(request.getNumberOfPairs().equals("2") && request.getSubjectOfLector().equals("fizo"));

        logger.info("Flush request {}" + request);
        daoRequestFromLector.flushRequestForLector(request);
        request = daoRequestFromLector.getAllRequestsFromLectorByIdLector(user.getIdLector()).get(0);
        assertTrue(request.getNumberOfPairs().equals("0") && request.getSubjectOfLector().equals("0000"));

    }

    @Test
    public void testDeleteAllRequestforUser() {
        logger.info("Create new user {} + name = Joe Frasier");
        Lector user = new Lector("Joe Frasier", "joe", "1111", "mail@mail.com");
        daoLector.saveOrUpdateLector(user);
        user = daoLector.getLectorByName("Joe Frasier");
        logger.info("New user created {} name = " + user.getNameLector());
        logger.info("Create request for new User {}" + user);
        List <String> groupes = Arrays.asList(new String[]{"e1", "e2", "e3", "e4", "e5"});
        user = daoLector.getLectorByName("Joe Frasier");
        List<RequestFromLector> requests = (List<RequestFromLector>) daoRequestFromLector.createEmptyRequestsForNewLector(user.getIdLector(), groupes);

        List<RequestFromLector> requests1 = (List<RequestFromLector>) requests.stream()
                .map(request  -> {request.setNumberOfPairs("3");
                                  return request;
                                  }).collect(Collectors.toList());

        requests = daoRequestFromLector.updateAllRequestsForLector(requests1);
        boolean ifChanged = true;
        for (RequestFromLector req : requests){
            ifChanged = ifChanged && (req.getNumberOfPairs().equals("3"));
        }
        logger.info("Created all requests for new User {}" + ifChanged);
        assertTrue(ifChanged);

        logger.info("Flush all requests for new User {}" + user);
        daoRequestFromLector.deleteAllRequestsFromLector(user.getIdLector());
        requests = daoRequestFromLector.getAllRequestsFromLectorByIdLector(user.getIdLector());
        assertTrue(requests.size() == 0);

    }

}
