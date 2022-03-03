package com.epam.brest.rest.application;

import com.epam.brest.model.Groupe;
import com.epam.brest.serviceapi.GroupeServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class GroupeRestClass {

    @Autowired
    private GroupeServiceApi groupeService;


    @GetMapping("/groupe/all")
    @Transactional(readOnly = true)
    public List<Groupe> allGroupes()
    {
        return (List<Groupe>) groupeService.getAllGroupesService();
    }

    @GetMapping("/groupe/get/name/all")
    @Transactional(readOnly = true)
    public List<String> allGroupesByNames()
    {
        return (List<String>) groupeService.getAllGroupeNamesService();
    }


    @GetMapping("/groupe/new")
    public Groupe insertNewGroupe(@RequestParam String newName) {
        return (Groupe) groupeService.insertNewGroupeService(newName);
    }

    @GetMapping("/groupe/update")
    public Groupe updateNameGroupe(@RequestParam String newName , @RequestParam String oldName) {
        return (Groupe) groupeService.updateGroupeNameService(newName, oldName);
    }


    @GetMapping("/groupe/delete")
    public String deleteGroupeByName(@RequestParam String name) {
        return (String) groupeService.deleteGroupeByNameService(name);
    }

    @GetMapping("/groupe/get/name")
    @Transactional(readOnly = true)
    public Groupe getGroupeByName(@RequestParam String name) {
        return (Groupe) groupeService.getGroupeByNameService(name);
    }
}
