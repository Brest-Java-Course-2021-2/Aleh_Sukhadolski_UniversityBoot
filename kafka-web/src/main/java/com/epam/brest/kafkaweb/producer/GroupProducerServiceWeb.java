package com.epam.brest.kafkaweb.producer;

import com.epam.brest.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

public class GroupProducerServiceWeb {

    @Autowired
    private KafkaTemplate<String, String> stringGroupKafkaTemplate;
    @Autowired
    private KafkaTemplate <String, Group> groupKafkaTemplate;
    @Autowired
    private KafkaTemplate<String, List<Group>> listGroupKafkaTemplate;


    private String givallgroups = "giveallgroups";
    private String newgroupcreated = "newgroup";
    private String updatedgroup = "updategroup";
    private String deletedgroup = "deletegroup";


    public void sendGiveAllGroups(List<Group> groups) { listGroupKafkaTemplate.send(givallgroups, groups); }
    public void sendCreateNewGroup(Group group) {
        groupKafkaTemplate.send(newgroupcreated, group);
    }
    public void sendUpdateGroup(Group group) {
        groupKafkaTemplate.send(updatedgroup, group);
    }
    public void sendDeleteGroup(String id) {
        stringGroupKafkaTemplate.send(deletedgroup, id);
    }

}
