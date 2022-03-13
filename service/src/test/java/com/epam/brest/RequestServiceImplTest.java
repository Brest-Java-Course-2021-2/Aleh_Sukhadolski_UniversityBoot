package com.epam.brest;

import com.epam.brest.serviceapi.GroupeServiceApi;
import com.epam.brest.serviceapi.RequestServiceApi;
import com.epam.brest.serviceapi.UserServiceApi;
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

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootApplication
@SpringBootTest (classes= { UserServiceImpl.class, RequestServiceImpl.class, GroupeServiceImpl.class
                            , UserDao.class, RequestDao.class, GroupeDao.class})
@ComponentScan("com.epam.brest.*")
@EntityScan("com.epam.brest")
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
        List<Group> grup = Arrays.stream(groupes)
                .map(gr -> groupeService.insertNewGroupeService(gr))
                .collect(Collectors.toList());
        userService.saveNewUserService(new Lector("TOMMY", "tom", "1111", "iuy@aa.com"));
    }


    @Test
    public void isGetAllRequests() {
        logger.info("GET ALL REQUESTS BY USER {}");
        Lector user = userService.getUserByNameService("TOMMY");
        List<RequestFromLector> requests = requestService.getAllRequestsService(user.getIdLector());
        assertTrue(requests.size() == 6);

        RequestFromLector request = requestService.getRequestByIdrService(requests.get(0).getIdRequest());
        assertTrue(request.getGroup().equals(requests.get(0).getGroup()));
    }

    @Test
    public void isSaveRequestsForNewUserAndNewGroupe() {
        logger.info("SAVE REQUESTS FOE NEW USER {}");
        Lector user = userService.saveNewUserService(new Lector(
                "MIKE", "mike", "2222", "mike@tyson.com"));

        List<RequestFromLector> requests = requestService.getAllRequestsService(user.getIdLector());
        assertTrue(requests.size() == 6);
        assertTrue(user.getNameLector().equals("MIKE"));

        requestService.saveRequestsWhenNewGroupeService("q1");
        requests = requestService.getAllRequestsService(user.getIdLector());
        assertTrue(requests.size() == 7);

        user = userService.getUserByNameService("TOMMY");
        requests = requestService.getAllRequestsService(user.getIdLector());
        assertTrue(requests.size() == 7);

        List<Group> groupes = groupeService.getAllGroupesService();
        assertTrue(groupes.size() == 7);
    }


    @Test
    public void isFlushRequests() {
        logger.info("SAVE REQUESTS FOE NEW USER {}");
        Lector user = userService.saveNewUserService(new Lector(
                "MIKE", "mike", "2222", "mike@tyson.com"));
        List<RequestFromLector> requests = requestService.getAllRequestsService(user.getIdLector());
        assertTrue(requests.size() == 6);
        assertTrue(requests.get(0).getNumberOfPairs().equals("0"));
        assertTrue(user.getNameLector().equals("MIKE"));
        requests = requests.stream().peek(req -> req.setNumberOfPairs("2")).collect(Collectors.toList());
        requests = requestService.updateAllRequestsForUserService(requests);
        assertTrue(requests.get(0).getNumberOfPairs().equals("2"));
        assertTrue(requests.get(1).getNumberOfPairs().equals("2"));
        RequestFromLector request = requestService.flushRequestInfoService(requests.get(0));
        assertTrue(request.getNumberOfPairs().equals("0"));
        assertTrue(requests.get(1).getNumberOfPairs().equals("2"));
    }


    @Test
    public void isDeleteRequests(){
        logger.info("DELETE REQUESTS FOR USER {}");
        Lector user = userService.saveNewUserService(new Lector(
                "MIKE", "mike", "2222", "mike@tyson.com"));
        List<RequestFromLector> requests = requestService.getAllRequestsService(user.getIdLector());
        assertTrue(requests.size() == 6);
        assertTrue(user.getNameLector().equals("MIKE"));
        userService.deleteUserByIdService(user.getIdLector());
        requests = requestService.getAllRequestsService(user.getIdLector());
        assertTrue(requests.size() == 0);
    }
}
