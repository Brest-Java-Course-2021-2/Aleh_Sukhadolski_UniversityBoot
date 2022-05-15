package com.epam.brest.kafkarest;

import com.epam.brest.*;
import com.epam.brest.kafkarest.config.GroupKafkaProducerConfigRest;
import com.epam.brest.kafkarest.consumer.GroupConsumerServiceRest;
import com.epam.brest.kafkarest.producer.GroupProducerServiceRest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest  (classes= {GroupServiceImpl.class, DaoLectorImpl.class, DaoRequestFromLectorImpl.class
		                  , DaoGroupImpl.class, LectorServiceImpl.class, RequestFromLectorServiceImpl.class
						  , GroupKafkaProducerConfigRest.class})

@Transactional()
@DirtiesContext
public class KafkaRestApplicationTests {

	@Autowired
	GroupServiceApi groupService;

	@Autowired
	LectorServiceApi lectorService;

	@Autowired
	RequestFromLectorServiceApi requestFromLectorService;

	@Autowired
	GroupConsumerServiceRest groupConsumerServiceRest;

	@Autowired
	GroupProducerServiceRest groupProducerServiceRest;
	@Test
	void contextLoads() {
	}
	@Test
	void isGroups(){
		Group group = groupService.createNewGroupService("eeee");
		assertTrue(group.getGroupName().equals("eeee"));
		List<Group> groups = groupService.getAllGroupsService();
		assertTrue(groups.size()>0);

	}



}
