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

    private String givallgroups = "giveallgroups";
    private String newgroupcreated = "newgroupcreated";
    private String updatedgroup = "updatedgroup";
    private String deletedgroup = "deletedgroup";


    public void sendGiveAllGroups(String msg) {
        stringGroupKafkaTemplate.send(givallgroups, msg);
    }
    public void sendCreateNewGroup(Group msg) {
        groupKafkaTemplate.send(newgroupcreated, msg);
    }
    public void sendUpdateGroup(List <Group> list) {
      listGroupKafkaTemplate.send(updatedgroup, list);
    }
    public void sendDeleteGroup(String id) {
        stringGroupKafkaTemplate.send(deletedgroup, id);
    }

}
