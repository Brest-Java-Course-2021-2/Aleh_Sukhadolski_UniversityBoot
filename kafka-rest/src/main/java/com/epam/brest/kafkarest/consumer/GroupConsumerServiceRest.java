package com.epam.brest.kafkarest.consumer;

import com.epam.brest.Group;
import com.epam.brest.GroupServiceApi;
import com.epam.brest.LectorServiceApi;
import com.epam.brest.RequestFromLectorServiceApi;
import com.epam.brest.kafkarest.producer.GroupProducerServiceRest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/*@Component
@ComponentScan("com.epam.brest")
@EntityScan("com.epam.brest")*/
@Service
@EnableJpaRepositories(basePackages = "com.epam.brest")
public class GroupConsumerServiceRest {

    private final Logger logger = LogManager.getLogger(GroupConsumerServiceRest.class);
    @Autowired
    private final ConsumerFactory<String, String> stringKafkaTemplate;

   @Autowired
    private final ConsumerFactory <String, Group> groupKafkaTemplate;

    @Autowired
    private final ConsumerFactory <String, List<Group>> listGroupKafkaTemplate;

    @Autowired
    private final GroupServiceApi groupService;


    @Autowired
    private final LectorServiceApi lectorService;

    @Autowired
    private final RequestFromLectorServiceApi requestFromLectorService;


    @Autowired
    private final GroupProducerServiceRest groupProducerService;

    public GroupConsumerServiceRest(ConsumerFactory<String, String> stringKafkaTemplate
                              , ConsumerFactory<String, Group> groupKafkaTemplate
                              , ConsumerFactory<String, List<Group>> listGroupKafkaTemplate,
            /*, GroupServiceImpl groupService*/
                              /*, GroupProducerServiceRest groupProducerService*/GroupServiceApi groupService
           , LectorServiceApi lectorService
            , RequestFromLectorServiceApi requestFromLectorService
            , GroupProducerServiceRest groupProducerService) {
        this.stringKafkaTemplate = stringKafkaTemplate;
        this.groupKafkaTemplate = groupKafkaTemplate;
        this.listGroupKafkaTemplate = listGroupKafkaTemplate;
        this.groupService = groupService;
        this.lectorService = lectorService;
        this.requestFromLectorService = requestFromLectorService;
        /*this.groupService = groupService;*/
        /*this.groupProducerService = groupProducerService;*/
        this.groupProducerService = groupProducerService;
    }


    @KafkaListener(topics = "giveallgroups", groupId = "string")
    public void listenGroupFoo(String message) {
        logger.info("Received Message in string : " + message);
       /* List<Group> groups = new ArrayList<>();
        Group group = new Group(111,"q1");
        groups.add(group);
        group = new Group(122,"qw");
        groups.add(group);*/
        List<Group> groups = groupService.getAllGroupsService();
        if (groups == null){ groups = new ArrayList<>();
        }
        logger.info("Groups in consumer" + groups.toString());
        groupProducerService.sendGiveAllGroups(groups);
    }

/*

    @KafkaListener(topics = "greeting"*//*, groupId = "greeting",
                             containerFactory = "greetingKafkaListenerContainerFactory"*//*)
    public void greetingListener(String greeting) throws Exception {

        try{
            ObjectMapper mapper = new ObjectMapper();
            Group getting = mapper.readValue(greeting, Group.class);
            System.out.println("Received Message in greeting : " + getting.toString());
        }catch (Exception ex){

            throw new SerializationException(ex);
        }
    }


    @KafkaListener(topics = "listgreeting"*//*, groupId = "greeting",
                             containerFactory = "greetingKafkaListenerContainerFactory"*//*)
    public void greetingListListener(String greeting) throws Exception {

        try{
            ObjectMapper mapper = new ObjectMapper();
            List<Group> getting = mapper.readValue(greeting, List.class);
            System.out.println("Received Message in greeting : " + getting.toString());
        }catch (Exception ex){

            throw new SerializationException(ex);
        }
    }*/
}
