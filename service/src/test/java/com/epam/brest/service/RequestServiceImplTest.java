package com.epam.brest.service;

import com.epam.brest.model.Groupe;
import com.epam.brest.model.Request;
import com.epam.brest.model.User;
import com.epam.brest.serviceapi.GroupeServiceApi;
import com.epam.brest.serviceapi.RequestServiceApi;
import com.epam.brest.serviceapi.UserServiceApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ComponentScan("com.epam.brest.*")
@EntityScan("com.epam.brest.model")
@Transactional()
public class RequestServiceImplTest {

    private final Logger logger = LogManager.getLogger(RequestServiceImplTest.class);

    @Autowired
    private UserServiceApi userService;

    @Autowired
    private RequestServiceApi requestService;

    @Autowired
    private GroupeServiceApi groupeService;

    @BeforeEach
    public void setUp() {
        String[] groupes = new String[]{"e1", "e2", "e3", "e4", "e5", "e6"};
        List<Groupe> grup = Arrays.stream(groupes)
                .map(gr -> groupeService.insertNewGroupeService(gr))
                .collect(Collectors.toList());
        userService.saveNewUserService(new User("TOMMY", "tom", "1111", "iuy@aa.com"));
    }


    @Test
    public void isGetAllRequests() {
        logger.info("GET ALL REQUESTS BY USER {}");
        User user = userService.getUserByNameService("TOMMY");
        List<Request> requests = requestService.getAllRequestsService(user.getId());
        assertTrue(requests.size() == 6);

        Request request = requestService.getRequestByIdrService(requests.get(0).getIdR());
        assertTrue(request.getGroupe().equals(requests.get(0).getGroupe()));
    }

    @Test
    public void isSaveRequestsForNewUserAndNewGroupe() {
        logger.info("SAVE REQUESTS FOE NEW USER {}");
        User user = userService.saveNewUserService(new User(
                "MIKE", "mike", "2222", "mike@tyson.com"));

        List<Request> requests = requestService.getAllRequestsService(user.getId());
        assertTrue(requests.size() == 6);
        assertTrue(user.getName().equals("MIKE"));

        requestService.saveRequestsWhenNewGroupeService("q1");
        requests = requestService.getAllRequestsService(user.getId());
        assertTrue(requests.size() == 7);

        user = userService.getUserByNameService("TOMMY");
        requests = requestService.getAllRequestsService(user.getId());
        assertTrue(requests.size() == 7);

        List<Groupe> groupes = groupeService.getAllGroupesService();
        assertTrue(groupes.size() == 7);
    }


    @Test
    public void isFlushRequests() {
        logger.info("SAVE REQUESTS FOE NEW USER {}");
        User user = userService.saveNewUserService(new User(
                "MIKE", "mike", "2222", "mike@tyson.com"));
        List<Request> requests = requestService.getAllRequestsService(user.getId());
        assertTrue(requests.size() == 6);
        assertTrue(user.getName().equals("MIKE"));
        requests = requests.stream().peek(req -> req.setPairs("2")).collect(Collectors.toList());
        requests = requestService.updateAllRequestsForUserService(requests);
        assertTrue(requests.get(0).getPairs().equals("2"));
        assertTrue(requests.get(1).getPairs().equals("2"));
        Request request = requestService.flushRequestInfoService(requests.get(0));
        assertTrue(request.getPairs().equals("0"));
    }


    @Test
    public void isDeleteRequests(){
        logger.info("DELETE REQUESTS FOR USER {}");
        User user = userService.saveNewUserService(new User(
                "MIKE", "mike", "2222", "mike@tyson.com"));
        List<Request> requests = requestService.getAllRequestsService(user.getId());
        assertTrue(requests.size() == 6);
        assertTrue(user.getName().equals("MIKE"));
        userService.deleteUserByIdService(user.getId());
        requests = requestService.getAllRequestsService(user.getId());
        assertTrue(requests.size() == 0);
    }
}
