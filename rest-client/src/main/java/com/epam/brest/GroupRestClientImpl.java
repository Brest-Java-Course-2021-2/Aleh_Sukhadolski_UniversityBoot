package com.epam.brest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;


public class GroupRestClientImpl implements GroupServiceApi {

    private final Logger logger = LoggerFactory.getLogger(GroupRestClientImpl.class);

    private RestTemplate restTemplate;

    @Override
    public List<String> getAllGroupNamesService() {
        logger.debug("GetAll Groups names ()");
        ResponseEntity responseEntity = restTemplate.getForEntity("/groups/get-all-names", List.class);
        return (List<String>) responseEntity.getBody();
    }

    @Override
    public List<Group> getAllGroupsService() {
        logger.debug("GetAll Groups ()");
        ResponseEntity responseEntity = restTemplate.getForEntity("/groups/get-all", List.class);
        return (List<Group>) responseEntity.getBody();
    }

    @Override
    public String deleteGroupByGroupNameService(String name) {
        logger.debug("Delete group by name () " + name);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> result =
                restTemplate.exchange("/groups/group/delete", HttpMethod.DELETE, entity, String.class);
        return result.getBody();
    }

    @Override
    public Group createNewGroupService(String newName) {
        logger.debug("Create group  () " + newName);
        ResponseEntity responseEntity = restTemplate.postForEntity("/groups/group/new", newName, Group.class);
        return (Group) responseEntity.getBody();
    }

    @Override
    public Group getGroupByGroupNameService(String name) {
        logger.debug("Get Group ()");
        ResponseEntity responseEntity = restTemplate.getForEntity(String.format("/groups/group/get-name?name=%s", name),
                                                                                Group.class);
        return (Group) responseEntity.getBody();    }

    @Override
    public Group updateGroupNameService(String newName, String oldName) {
        logger.debug("Update group  () " + newName + " to " + oldName);
        String [] names = new String[] {newName, oldName};
        HttpEntity<String[]> entity = new HttpEntity<>(names);
        ResponseEntity<Group> result = restTemplate.exchange("/groups/group/update",
                HttpMethod.PUT, entity, Group.class);
        return result.getBody();
    }
}
