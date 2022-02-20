package com.epam.brest.daoAPI;

import com.epam.brest.model.Groupe;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest.model")
public interface DaoGroupeApi {

    List<String> getAllGroupeNames();

    List<Groupe> getAllGroupes();

    String deleteGroupeByName();

    Groupe insertNewGroupe();
}
