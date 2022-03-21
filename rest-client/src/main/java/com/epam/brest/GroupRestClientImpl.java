package com.epam.brest;

import com.epam.brest.serviceapi.GroupServiceApi;

import java.util.List;

public class GroupRestClientImpl implements GroupServiceApi {
    @Override
    public List<String> getAllGroupNamesService() {
        return null;
    }

    @Override
    public List<Group> getAllGroupsService() {
        return null;
    }

    @Override
    public String deleteGroupByGroupNameService(String name) {
        return null;
    }

    @Override
    public Group createNewGroupService(String newName) {
        return null;
    }

    @Override
    public Group getGroupByGroupNameService(String name) {
        return null;
    }

    @Override
    public Group updateGroupNameService(String newName, String oldName) {
        return null;
    }
}
