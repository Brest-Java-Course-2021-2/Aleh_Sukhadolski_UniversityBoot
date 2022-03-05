package com.epam.brest.daoAPI;

import com.epam.brest.Groupe;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest.model")
public interface DaoGroupeApi {

    List<String> getAllGroupeNames();

    List<Groupe> getAllGroupes();

    String deleteGroupeByName(String name);

    Groupe insertNewGroupe(String newName);

    Groupe getGroupeByName(String name);

    Groupe updateGroupeName(String newName, String oldName);
}
