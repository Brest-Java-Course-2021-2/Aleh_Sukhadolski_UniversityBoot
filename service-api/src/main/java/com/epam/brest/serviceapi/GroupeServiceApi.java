package com.epam.brest.serviceapi;

import com.epam.brest.model.Groupe;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest.model")
public interface GroupeServiceApi {

    List<String> getAllGroupeNamesService();

    List<Groupe> getAllGroupesService();

    String deleteGroupeByNameService(String name);

    Groupe insertNewGroupeService(String newName);

    Groupe getGroupeByNameService(String name);

    Groupe updateGroupeNameService(String newName, String oldName);
}
