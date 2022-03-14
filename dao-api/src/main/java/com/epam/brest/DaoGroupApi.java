package com.epam.brest;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest")
public interface DaoGroupApi {

    List<String> getAllGroupsNames();

    List<Group> getAllGroups();

    String deleteGroupByName(String name);

    Group insertNewGroup(String newName);

    Group getGroupByName(String name);

    Group updateGroup(String newName, String oldName);
}
