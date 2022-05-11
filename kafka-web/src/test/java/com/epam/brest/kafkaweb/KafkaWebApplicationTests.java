package com.epam.brest.kafkaweb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
class KafkaWebApplicationTests {

	@Test
	void contextLoads() {
	}

}
