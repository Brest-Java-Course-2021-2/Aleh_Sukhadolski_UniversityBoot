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
@SpringBootTest (classes= { UserDao.class, RequestDao.class, GroupeDao.class})
//@ContextConfiguration
@ComponentScan("com.epam.brest.*")
@EntityScan("com.epam.brest")
@Transactional()
public class RequestRepositoryTest {

    private final Logger logger = LogManager.getLogger(RequestRepositoryTest.class);

    @Autowired
    private  DaoRequestApi requestDao;

    @Autowired
    private  DaoUserApi userDao;


    @Test
    public void testRequestsForNewUserAndNewGroupe() {
        logger.info("Create new user {}");
        User user = new User("Joe Frasier", "joe", "1111", "mail@mail.com");
        userDao.saveAndUpdateUser(user);
        user = userDao.getUserByName("Joe Frasier");
        logger.info("Requests for new User {}" + user);
        List <String> groupes = Arrays.asList(new String[]{"e1", "e2", "e3", "e4", "e5"});
        requestDao.saveRequestsForNewUser(user.getId(),groupes);
        List<Request> requests = requestDao.getAllRequests(user.getId());
        assertTrue(requests.size() == 5);
        List<Integer> usersId = (List<Integer>) userDao.getAllUsers()
                                                    .stream()
                                                    .flatMap(us -> Stream.of(us.getId()))
                                                    .collect(Collectors.toList());
        requestDao.saveRequestsWhenNewGroupe("e6", usersId);
        requests = requestDao.getAllRequests(user.getId());
        assertTrue(requests.size() == 6);

        List<User> users = userDao.getAllUsers();
        for (User us : users){
            requests = requestDao.getAllRequests(us.getId());
            assertTrue(requests.size() == 6);
        }
    }

    @Test
    public void testCreateAndDeleteRequest() {
        logger.info("Create new user {} + name = Joe Frasier");
        User user = new User("Joe Frasier", "joe", "1111", "mail@mail.com");
        userDao.saveAndUpdateUser(user);
        user = userDao.getUserByName("Joe Frasier");
        logger.info("New user created {} name = " + user.getName());
        logger.info("Create request for new User {}" + user);
        List <String> groupes = Arrays.asList(new String[]{"e1", "e2", "e3", "e4", "e5"});
        user = userDao.getUserByName("Joe Frasier");
        List<Request> requests = (List<Request>) requestDao.saveRequestsForNewUser(user.getId(), groupes);
        Request request = requestDao.getAllRequests(user.getId()).get(0);
        request.setPairs("2");
        request.setSubject("fizo");
        requestDao.updateRequest(request);
        request = requestDao.getAllRequests(user.getId()).get(0);
        assertTrue(request.getPairs().equals("2") && request.getSubject().equals("fizo"));

        logger.info("Flush request {}" + request);
        requestDao.flushRequestInfo(request);
        request = requestDao.getAllRequests(user.getId()).get(0);
        assertTrue(request.getPairs().equals("0") && request.getSubject().equals("0000"));

    }

    @Test
    public void testDeleteAllRequestforUser() {
        logger.info("Create new user {} + name = Joe Frasier");
        User user = new User("Joe Frasier", "joe", "1111", "mail@mail.com");
        userDao.saveAndUpdateUser(user);
        user = userDao.getUserByName("Joe Frasier");
        logger.info("New user created {} name = " + user.getName());
        logger.info("Create request for new User {}" + user);
        List <String> groupes = Arrays.asList(new String[]{"e1", "e2", "e3", "e4", "e5"});
        user = userDao.getUserByName("Joe Frasier");
        List<Request> requests = (List<Request>) requestDao.saveRequestsForNewUser(user.getId(), groupes);

        List<Request> requests1 = (List<Request>) requests.stream()
                .map(request  -> {request.setPairs("3");
                                  return request;
                                  }).collect(Collectors.toList());

        requests = requestDao.updateAllRequestsForUser(requests1);
        boolean ifChanged = true;
        for (Request req : requests){
            ifChanged = ifChanged && (req.getPairs().equals("3"));
        }
        logger.info("Created all requests for new User {}" + ifChanged);
        assertTrue(ifChanged);

        logger.info("Flush all requests for new User {}" + user);
        requestDao.deleteAllRequestsOfUser(user.getId());
        requests = requestDao.getAllRequests(user.getId());
        assertTrue(requests.size() == 0);

    }

}
