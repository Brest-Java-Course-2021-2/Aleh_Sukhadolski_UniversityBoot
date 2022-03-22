package com.epam.brest;

import com.epam.brest.Group;

import java.util.List;

public interface GroupServiceApi {

    List<String> getAllGroupNamesService();

    List<Group> getAllGroupsService();

    String deleteGroupByGroupNameService(String name);

    Group createNewGroupService(String newName);

    Group getGroupByGroupNameService(String name);

    Group updateGroupNameService(String newName, String oldName);

}
