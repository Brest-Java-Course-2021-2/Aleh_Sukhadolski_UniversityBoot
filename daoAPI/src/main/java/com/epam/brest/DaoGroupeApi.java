package com.epam.brest;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest")
public interface DaoGroupeApi {

    List<String> getAllGroupeNames();

    List<Group> getAllGroupes();

    String deleteGroupeByName(String name);

    Group insertNewGroupe(String newName);

    Group getGroupeByName(String name);

    Group updateGroupeName(String newName, String oldName);
}
