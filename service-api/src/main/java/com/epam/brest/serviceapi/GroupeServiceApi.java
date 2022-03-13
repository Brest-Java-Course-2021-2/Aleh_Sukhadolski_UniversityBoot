package com.epam.brest.serviceapi;

import com.epam.brest.Group;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest.model")
public interface GroupeServiceApi {

    List<String> getAllGroupeNamesService();

    List<Group> getAllGroupesService();

    String deleteGroupeByNameService(String name);

    Group insertNewGroupeService(String newName);

    Group getGroupeByNameService(String name);

    Group updateGroupeNameService(String newName, String oldName);
}
