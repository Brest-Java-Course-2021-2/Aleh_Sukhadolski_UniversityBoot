package com.epam.brest.service.library;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest("service.message=Hello guys")
public class MyServiceTest {

    @Autowired
    private MyService myService;

    @Test
    public void contextLoads() {
        assertThatObject(myService);
        assertThat(myService.message()).isNotNull();
        assertThat(myService.message().equals("Hello guys"));
    }

    @SpringBootApplication
    static class TestConfiguration {
    }

}

