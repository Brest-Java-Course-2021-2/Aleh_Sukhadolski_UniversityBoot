package com.epam.brest;

import com.epam.brest.serviceapi.GroupeServiceApi;
import com.epam.brest.serviceapi.RequestServiceApi;
import com.epam.brest.serviceapi.UserServiceApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
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

@SpringBootApplication
@SpringBootTest (classes= { UserServiceImpl.class, RequestServiceImpl.class, GroupeServiceImpl.class
                          , UserDao.class, RequestDao.class, GroupeDao.class})
@ComponentScan("com.epam.brest.*")
@EntityScan("com.epam.brest")
@Transactional()
public class GroupeServiceImplTest {

    private final Logger logger = LogManager.getLogger(GroupeServiceImplTest.class);

    @Autowired
    private GroupeServiceApi groupeService;

    @Autowired
    private UserServiceApi userService;

    @Autowired
    private RequestServiceApi requestService;


    @BeforeEach
    public void setUp() {
        String[] groupes = new String[]{"e1", "e2", "e3", "e4", "e5", "e6"};
        List<Groupe> grup = Arrays.stream(groupes)
                .map(gr -> groupeService.insertNewGroupeService(gr))
                .collect(Collectors.toList());
        User user = userService.saveNewUserService(new User("TOMMY", "tom", "1111", "iuy@aa.com"));
    }

    @Test
    public void isGroupesTest() {
        logger.info("GET ALL GROUPES {}");
        List<String> groupes = groupeService.getAllGroupeNamesService();
        Assertions.assertTrue(groupes.size() == 6);
        List<Groupe> group = groupeService.getAllGroupesService();
        Assertions.assertTrue(group.size() == 6);
    }

    @Test
    public void isInsertNewGroupeTest() {
        logger.info("INSERT NEW GROUPE {}");
        User user = userService.getUserByNameService("TOMMY");
        List<Request> requests = requestService.getAllRequestsService(user.getId());
        Assertions.assertTrue(requests.size() == 6);
        Groupe groupe = groupeService.insertNewGroupeService("e7");
        Assertions.assertTrue(groupe.getGroupe().equals("e7"));
        List<Groupe> group = groupeService.getAllGroupesService();
        Assertions.assertTrue(group.size() == 7);
        user = userService.getUserByNameService("TOMMY");
        requests = requestService.getAllRequestsService(user.getId());
        Assertions.assertTrue(requests.size() == 7);

    }


    @Test
    public void isUpdateGroupeNameTest() {
        logger.info("UPDATE GROUPE {}");
        groupeService.updateGroupeNameService("w1", "e6");
        Groupe groupe = groupeService.getGroupeByNameService("w1");
        Assertions.assertTrue(groupe.getGroupe().equals("w1"));
        groupe = groupeService.getGroupeByNameService("e6");
        Assertions.assertFalse(groupe.getGroupe().equals("e6"));
        List<Groupe> group = groupeService.getAllGroupesService();
        Assertions.assertTrue(group.size() == 6);
        User user = userService.getUserByNameService("TOMMY");
        List<Request> requests = requestService.getAllRequestsService(user.getId());
        boolean ifExist = false;
        boolean ifNotExist = true;
        for (Request request : requests){
            if (request.getGroupe().equals("w1")){ ifExist = true; }
            if (request.getGroupe().equals("e6")){ ifNotExist = false; }
        }
        Assertions.assertTrue(ifExist);
        Assertions.assertTrue(ifNotExist);
        requests = requestService.getAllRequestsService(user.getId());
        Assertions.assertTrue(requests.size() == 6);
    }

    @Test
    public void isDeleteGroupeTest() {
        logger.info("DELETE GROUPE {}");
        String result = groupeService.deleteGroupeByNameService("e1");
        Assertions.assertTrue(result.equals("e1"));
        Groupe groupe = groupeService.getGroupeByNameService("e1");
        Assertions.assertTrue(groupe.getIdG() == 0);

    }
}