package com.epam.brest.kafkarest.consumer;

import com.epam.brest.Group;
import com.epam.brest.GroupServiceApi;
import com.epam.brest.kafkarest.producer.GroupProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;

import java.util.List;

public class GroupConsumerService {

    @Autowired
    private final ConsumerFactory<String, String> stringKafkaTemplate;

   @Autowired
    private final ConsumerFactory <String, Group> groupKafkaTemplate;

    @Autowired
    private final ConsumerFactory <String, List<Group>> listGroupKafkaTemplate;

    @Autowired
    private final GroupServiceApi groupService;

    private final GroupProducerService groupProducerService;

    public GroupConsumerService(ConsumerFactory<String, String> stringKafkaTemplate
                              , ConsumerFactory<String, Group> groupKafkaTemplate
                              , ConsumerFactory<String, List<Group>> listGroupKafkaTemplate
                              , GroupServiceApi groupService, GroupProducerService groupProducerService) {
        this.stringKafkaTemplate = stringKafkaTemplate;
        this.groupKafkaTemplate = groupKafkaTemplate;
        this.listGroupKafkaTemplate = listGroupKafkaTemplate;
        this.groupService = groupService;
        this.groupProducerService = groupProducerService;
    }


    @KafkaListener(topics = "giveallgroups", groupId = "listgroup")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in string : " + message);
        List<Group> groups = groupService.getAllGroupsService();
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
