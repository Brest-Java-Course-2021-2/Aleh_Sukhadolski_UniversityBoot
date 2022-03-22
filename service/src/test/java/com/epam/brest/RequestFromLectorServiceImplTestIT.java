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

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootApplication
@SpringBootTest (classes= { LectorServiceImpl.class, RequestFromLectorServiceImpl.class, GroupServiceImpl.class
                            , DaoLectorImpl.class, DaoRequestFromLectorImpl.class, DaoGroupImpl.class})
@ComponentScan("com.epam.brest")
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
        String[] groups = new String[]{"e1", "e2", "e3", "e4", "e5", "e6"};
        List<Group> grup = Arrays.stream(groups)
                .map(gr -> groupService.createNewGroupService(gr))
                .collect(Collectors.toList());
        lectorService.createNewLectorService(new Lector("TOMMY", "tom", "1111", "iuy@aa.com"));
    }

    @Test
    public void isGetAllRequests() {
        logger.info("GET ALL REQUESTS BY USER {}");
        Lector lector = lectorService.getLectorByLectorsNameService("TOMMY");
        List<RequestFromLector> requestsFromLectorService = requestFromLectorService.getAllRequestsFromLectorService(lector.getIdLector());
        assertTrue(requestsFromLectorService.size() == 6);

        RequestFromLector request = requestFromLectorService.getRequestOfLectorByIdRequestService(requestsFromLectorService.get(0).getIdRequest());
        assertTrue(request.getGroup().equals(requestsFromLectorService.get(0).getGroup()));
    }

    @Test
    public void isSaveRequestsForNewLectorAndNewGroupe() {
        logger.info("SAVE REQUESTS FOR NEW Lector {}");
        Lector lector = lectorService.createNewLectorService(new Lector(
                "MIKE", "mike", "2222", "mike@tyson.com"));

        List<RequestFromLector> requestsFromLectorService = requestFromLectorService.getAllRequestsFromLectorService(lector.getIdLector());
        assertTrue(requestsFromLectorService.size() == 6);
        assertTrue(lector.getNameLector().equals("MIKE"));

        requestFromLectorService.saveRequestsForLectorsWhenCreateNewGroupeService("q1");
        requestsFromLectorService = requestFromLectorService.getAllRequestsFromLectorService(lector.getIdLector());
        assertTrue(requestsFromLectorService.size() == 7);

        lector = lectorService.getLectorByLectorsNameService("TOMMY");
        requestsFromLectorService = requestFromLectorService.getAllRequestsFromLectorService(lector.getIdLector());
        assertTrue(requestsFromLectorService.size() == 7);

        List<Group> groups = groupService.getAllGroupsService();
        assertTrue(groups.size() == 7);
    }

    @Test
    public void isFlushRequests() {
        logger.info("SAVE REQUESTS FOE NEW Lector {}");
        Lector lector = lectorService.createNewLectorService(new Lector(
                "MIKE", "mike", "2222", "mike@tyson.com"));
        List<RequestFromLector> requestsFromLectorService = requestFromLectorService.getAllRequestsFromLectorService(lector.getIdLector());
        assertTrue(requestsFromLectorService.size() == 6);
        assertTrue(requestsFromLectorService.get(0).getNumberOfPairs().equals("0"));
        assertTrue(lector.getNameLector().equals("MIKE"));
        requestsFromLectorService = requestsFromLectorService.stream()
                                                             .peek(req -> req.setNumberOfPairs("2"))
                                                             .collect(Collectors.toList());
        requestsFromLectorService = requestFromLectorService.updateAllRequestsForLectorsService(requestsFromLectorService);
        assertTrue(requestsFromLectorService.get(0).getNumberOfPairs().equals("2"));
        assertTrue(requestsFromLectorService.get(1).getNumberOfPairs().equals("2"));
        RequestFromLector requestFromLector = requestFromLectorService.flushRequestFromLectorService(requestsFromLectorService.get(0));
        assertTrue(requestFromLector.getNumberOfPairs().equals(" "));
        assertTrue(requestsFromLectorService.get(1).getNumberOfPairs().equals("2"));
    }

    @Test
    public void isDeleteRequests(){
        logger.info("DELETE REQUESTS FOR Lector {}");
        Lector lector = lectorService.createNewLectorService(new Lector(
                "MIKE", "mike", "2222", "mike@tyson.com"));
        List<RequestFromLector> requestsFromLectorService = requestFromLectorService.getAllRequestsFromLectorService(lector.getIdLector());
        assertTrue(requestsFromLectorService.size() == 6);
        assertTrue(lector.getNameLector().equals("MIKE"));
        lectorService.deleteLectorByIdLectorService(lector.getIdLector());
        requestsFromLectorService = requestFromLectorService.getAllRequestsFromLectorService(lector.getIdLector());
        assertTrue(requestsFromLectorService.size() == 0);
    }
}
