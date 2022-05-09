package com.epam.brest.kafkarest.producer;

import com.epam.brest.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

public class GroupProducerService {
    @Autowired
    private KafkaTemplate<String, String> stringGroupKafkaTemplate;
    @Autowired
    private KafkaTemplate <String, Group> groupKafkaTemplate;
    @Autowired
    private KafkaTemplate<String, List<Group>> listGroupKafkaTemplate;

    private String topicName1 = "getallgroups";
    private String topicName2 = "newgroup";
    private String topicName3 = "updategroup";

    private String topicName4 = "deletegroup";


    public void sendMessage(String msg) {
        stringGroupKafkaTemplate.send(topicName1, msg);
    }


    public void sendMessageGreeting(Group msg) {
        groupKafkaTemplate.send(topicName2, msg);
    }


    public void sendMessageListGreeting(List <Group> list) {
      listGroupKafkaTemplate.send(topicName3, list);
    }
}
