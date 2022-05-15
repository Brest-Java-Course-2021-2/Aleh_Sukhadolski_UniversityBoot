package com.epam.brest.kafkaweb.consumer;

import com.epam.brest.Group;
import com.epam.brest.GroupServiceApi;
import com.epam.brest.kafkaweb.producer.GroupProducerServiceWeb;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;

import java.util.List;

public class GroupConsumerServiceWeb {
    @Autowired
    private final ConsumerFactory<String, String> stringKafkaTemplate;
    @Autowired
    private final ConsumerFactory <String, Group> groupKafkaTemplate;
    @Autowired
    private final ConsumerFactory <String, List<Group>> listGroupKafkaTemplate;
    @Autowired
    private final GroupServiceApi groupService;
    private final GroupProducerServiceWeb groupProducerService;

    public GroupConsumerServiceWeb(ConsumerFactory<String, String> stringKafkaTemplate
            , ConsumerFactory<String, Group> groupKafkaTemplate
            , ConsumerFactory<String, List<Group>> listGroupKafkaTemplate
            , GroupServiceApi groupService
            , GroupProducerServiceWeb groupProducerService) {
        this.stringKafkaTemplate = stringKafkaTemplate;
        this.groupKafkaTemplate = groupKafkaTemplate;
        this.listGroupKafkaTemplate = listGroupKafkaTemplate;
        this.groupService = groupService;
        this.groupProducerService = groupProducerService;
    }

    @KafkaListener(topics = "sendallgroups", groupId = "listgroup")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in string : " + message);
        try{
            ObjectMapper mapper = new ObjectMapper();
            List<Group> groups = mapper.readValue(message, List.class);
            System.out.println("Received Message in greeting : " + groups.toString());
        }catch (Exception ex){

            throw new SerializationException(ex);
        }
    }

}
