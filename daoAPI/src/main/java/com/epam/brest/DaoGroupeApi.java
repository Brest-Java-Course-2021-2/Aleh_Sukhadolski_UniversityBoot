package com.epam.brest;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest")
public interface DaoGroupeApi {

    List<String> getAllGroupeNames();

    List<Groupe> getAllGroupes();

    String deleteGroupeByName(String name);

    Groupe insertNewGroupe(String newName);

    Groupe getGroupeByName(String name);

    Groupe updateGroupeName(String newName, String oldName);
}
