package com.epam.brest;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest")
public interface DaoGroupApi {

    List<String> getAllGroupsByNameGroups();

    List<Group> getAllGroups();

    String deleteGroupByNameGroup(String nameGroup);

    Group createNewGroup(String newNameGroup);

    Group getGroupByNameGroup(String nameGroup);

    Group updateGroupByNameGroup(String newNameGroup, String oldNameGroup);
}
