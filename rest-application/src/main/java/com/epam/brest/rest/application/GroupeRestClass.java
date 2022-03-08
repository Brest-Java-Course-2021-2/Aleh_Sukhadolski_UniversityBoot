package com.epam.brest.rest.application;

import com.epam.brest.Groupe;
import com.epam.brest.RequestDao;
import com.epam.brest.User;
import com.epam.brest.serviceapi.GroupeServiceApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GroupeRestClass {

    private final Logger logger = LogManager.getLogger(GroupeRestClass.class);

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


    @GetMapping("/groupe/get/name")
    @Transactional(readOnly = true)
    public Groupe getGroupeByName(@RequestParam String name) {
        return (Groupe) groupeService.getGroupeByNameService(name);
    }

    @PostMapping(path = "/groupe/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Groupe> createGroupe(@RequestBody String newName) {
        //logger.debug("createGroupe({})", newName);
        Groupe groupe = groupeService.insertNewGroupeService(newName);
        return new ResponseEntity<>(groupe, HttpStatus.OK);
    }


    @PutMapping(path = "/groupe/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Groupe> updateGroupe(@RequestBody List <String> names) {
        //logger.debug("updateGroupe({})", newName);
        Groupe groupe = groupeService.updateGroupeNameService(names.get(0), names.get(1));
        return new ResponseEntity<>(groupe, HttpStatus.OK);
    }


    @DeleteMapping(value = "/groupe/delete/{name}", produces = {"application/json"})
    public ResponseEntity <String> deleteGroupe(@PathVariable String name) {
        //logger.debug("deleteGroupe({})", name);
        name = groupeService.deleteGroupeByNameService(name);
        return new ResponseEntity(name, HttpStatus.OK);
    }



}
