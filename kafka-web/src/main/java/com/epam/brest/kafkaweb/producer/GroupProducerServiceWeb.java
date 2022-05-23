package com.epam.brest.kafkaweb.producer;

import com.epam.brest.Group;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupProducerServiceWeb {

    @Autowired
    private KafkaTemplate<String, String> stringGroupKafkaTemplate;
    @Autowired
    private KafkaTemplate <String, Group> groupKafkaTemplate;
    @Autowired
    private KafkaTemplate<String, List<Group>> listGroupKafkaTemplate;


    private String givallgroups = "giveallgroups";
    private String newgroup = "newgroup";
    private String updategroup = "updategroup";
    private String deletegroup = "deletegroup";


    public void sendGiveAllGroups(String message) { stringGroupKafkaTemplate.send(givallgroups, message); }
    public void sendCreateNewGroup(Group group) throws JsonProcessingException {
        groupKafkaTemplate.send(newgroup, group);
    }
    public void sendUpdateGroup(Group group) throws JsonProcessingException {
        groupKafkaTemplate.send(updategroup, group);
    }
    public void sendDeleteGroup(String id) {
        stringGroupKafkaTemplate.send(deletegroup, id);
    }

}
