package com.epam.brest.dao;

import com.epam.brest.dao.model.Request;
import com.epam.brest.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional()
public class RequestRepositoryTest {

    private final Logger logger = LogManager.getLogger(RequestRepositoryTest.class);

    @Autowired
    private RequestDao requestDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void testRequestsForNewUserAndNewGroupe() {
        logger.info("Create new user {}");
        User user = new User("Joe Frasier", "joe", "1111", "mail@mail.com");
        userDao.saveAndUpdateUser(user);
        user = userDao.getUserByName("Joe Frasier").get(0);
        logger.info("Requests for new User {}" + user);
        List <String> groupes = Arrays.asList(new String[]{"e1", "e2", "e3", "e4", "e5"});
        requestDao.saveRequestsForNewUser(user.getId(),groupes);
        List<Request> requests = requestDao.getAllRequests(user.getId());
        assertThat(requests.size() == 5);
        List<Integer> usersId = (List<Integer>) userDao.getAllUsers()
                                                    .stream()
                                                    .flatMap(us -> Stream.of(us.getId()))
                                                    .collect(Collectors.toList());
        requestDao.saveRequestsWhenNewGroupe("e6", usersId);
        requests = requestDao.getAllRequests(user.getId());
        assertThat(requests.size() == 6);

        List<User> users = userDao.getAllUsers();
        for (User us : users){
            requests = requestDao.getAllRequests(us.getId());
            assertThat(requests.size() == 6);
        }
    }




}
