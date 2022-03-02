package com.epam.brest.rest.application;

import com.epam.brest.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ServiceRestClassTest {

    @Autowired
    private ServiceRestClass serviceRestClass;

    @Test
    public void isRestClass (){
        User user = serviceRestClass.saveuser("Mik", "mik", "1111", "is@is.xc");
        assertTrue(user.getName().equals("Mik"));
    }

}