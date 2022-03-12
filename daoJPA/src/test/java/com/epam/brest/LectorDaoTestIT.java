package com.epam.brest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import javax.transaction.Transactional;

@SpringBootApplication
@SpringBootTest(classes= { UserDao.class, RequestDao.class, GroupeDao.class, LectorDao.class})
//@ContextConfiguration
@ComponentScan("com.epam.brest")
@EntityScan("com.epam.brest")
@Transactional()
public class LectorDaoTestIT {

    @Autowired
    private LectorJpaRepository lectorJpaRepository;

    @Autowired
    private DaoLectorApi daoLectorDao;
}
