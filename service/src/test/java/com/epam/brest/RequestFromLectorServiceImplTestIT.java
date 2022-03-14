package com.epam.brest;

import com.epam.brest.serviceapi.GroupServiceApi;
import com.epam.brest.serviceapi.RequestFromLectorServiceApi;
import com.epam.brest.serviceapi.LectorServiceApi;
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
@SpringBootTest (classes= { LectorServiceImpl.class, RequestFromLectorServiceImpl.class, GroupServiceImpl.class
                            , DaoLectorImpl.class, DaoRequestFromLectorImpl.class, DaoGroupImpl.class})
@ComponentScan("com.epam.brest.*")
@EntityScan("com.epam.brest")
@Transactional()
public class RequestFromLectorServiceImplTestIT {

    private final Logger logger = LogManager.getLogger(RequestFromLectorServiceImplTestIT.class);

    @Autowired
    private LectorServiceApi lectorService;

    @Autowired
    private RequestFromLectorServiceApi requestFromLectorService;

    @Autowired
    private GroupServiceApi groupService;

    @BeforeEach
    public void setUp() {
        String[] groupes = new String[]{"e1", "e2", "e3", "e4", "e5", "e6"};
        List<Group> grup = Arrays.stream(groupes)
                .map(gr -> groupService.createNewGroupService(gr))
                .collect(Collectors.toList());
        lectorService.createNewLectorService(new Lector("TOMMY", "tom", "1111", "iuy@aa.com"));
    }


    @Test
    public void isGetAllRequests() {
        logger.info("GET ALL REQUESTS BY USER {}");
        Lector user = lectorService.getLectorByLectorsNameService("TOMMY");
        List<RequestFromLector> requests = requestFromLectorService.getAllRequestsFromLectorService(user.getIdLector());
        assertTrue(requests.size() == 6);

        RequestFromLector request = requestFromLectorService.getRequestOfLectorByIdRequestService(requests.get(0).getIdRequest());
        assertTrue(request.getGroup().equals(requests.get(0).getGroup()));
    }

    @Test
    public void isSaveRequestsForNewUserAndNewGroupe() {
        logger.info("SAVE REQUESTS FOE NEW USER {}");
        Lector user = lectorService.createNewLectorService(new Lector(
                "MIKE", "mike", "2222", "mike@tyson.com"));

        List<RequestFromLector> requests = requestFromLectorService.getAllRequestsFromLectorService(user.getIdLector());
        assertTrue(requests.size() == 6);
        assertTrue(user.getNameLector().equals("MIKE"));

        requestFromLectorService.saveRequestsForLectorsWhenCreateNewGroupeService("q1");
        requests = requestFromLectorService.getAllRequestsFromLectorService(user.getIdLector());
        assertTrue(requests.size() == 7);

        user = lectorService.getLectorByLectorsNameService("TOMMY");
        requests = requestFromLectorService.getAllRequestsFromLectorService(user.getIdLector());
        assertTrue(requests.size() == 7);

        List<Group> groupes = groupService.getAllGroupsService();
        assertTrue(groupes.size() == 7);
    }


    @Test
    public void isFlushRequests() {
        logger.info("SAVE REQUESTS FOE NEW USER {}");
        Lector user = lectorService.createNewLectorService(new Lector(
                "MIKE", "mike", "2222", "mike@tyson.com"));
        List<RequestFromLector> requests = requestFromLectorService.getAllRequestsFromLectorService(user.getIdLector());
        assertTrue(requests.size() == 6);
        assertTrue(requests.get(0).getNumberOfPairs().equals("0"));
        assertTrue(user.getNameLector().equals("MIKE"));
        requests = requests.stream().peek(req -> req.setNumberOfPairs("2")).collect(Collectors.toList());
        requests = requestFromLectorService.updateAllRequestsForLectorsService(requests);
        assertTrue(requests.get(0).getNumberOfPairs().equals("2"));
        assertTrue(requests.get(1).getNumberOfPairs().equals("2"));
        RequestFromLector request = requestFromLectorService.flushRequestFromLectorService(requests.get(0));
        assertTrue(request.getNumberOfPairs().equals("0"));
        assertTrue(requests.get(1).getNumberOfPairs().equals("2"));
    }


    @Test
    public void isDeleteRequests(){
        logger.info("DELETE REQUESTS FOR USER {}");
        Lector user = lectorService.createNewLectorService(new Lector(
                "MIKE", "mike", "2222", "mike@tyson.com"));
        List<RequestFromLector> requests = requestFromLectorService.getAllRequestsFromLectorService(user.getIdLector());
        assertTrue(requests.size() == 6);
        assertTrue(user.getNameLector().equals("MIKE"));
        lectorService.deleteLectorByIdLectorService(user.getIdLector());
        requests = requestFromLectorService.getAllRequestsFromLectorService(user.getIdLector());
        assertTrue(requests.size() == 0);
    }
}
