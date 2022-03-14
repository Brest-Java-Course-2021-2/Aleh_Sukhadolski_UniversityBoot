package com.epam.brest.serviceapi;

import com.epam.brest.Group;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest.model")
public interface GroupServiceApi {

    List<String> getAllGroupNamesService();

    List<Group> getAllGroupsService();

    String deleteGroupByGroupNameService(String name);

    Group createNewGroupService(String newName);

    Group getGroupByGroupNameService(String name);

    Group updateGroupNameService(String newName, String oldName);
}
