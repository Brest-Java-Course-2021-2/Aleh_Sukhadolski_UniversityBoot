package com.epam.brest;

import com.epam.brest.serviceapi.GroupServiceApi;
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
public class GroupsRest {

    private final Logger logger = LogManager.getLogger(GroupsRest.class);

    @Autowired
    private GroupServiceApi groupService;


    @GetMapping("/groupe/all")
    @Transactional(readOnly = true)
    public List<Group> allGroupes()
    {
        return (List<Group>) groupService.getAllGroupsService();
    }

    @GetMapping("/groupe/get/name/all")
    @Transactional(readOnly = true)
    public List<String> allGroupesByNames()
    {
        return (List<String>) groupService.getAllGroupNamesService();
    }


    @GetMapping("/groupe/get/name")
    @Transactional(readOnly = true)
    public Group getGroupeByName(@RequestParam String name) {
        return (Group) groupService.getGroupByGroupNameService(name);
    }

    @PostMapping(path = "/groupe/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Group> createGroupe(@RequestBody String newName) {
        //logger.debug("createGroupe({})", newName);
        Group groupe = groupService.createNewGroupService(newName);
        return new ResponseEntity<>(groupe, HttpStatus.OK);
    }


    @PutMapping(path = "/groupe/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Group> updateGroupe(@RequestBody List <String> names) {
        //logger.debug("updateGroupe({})", newName);
        Group groupe = groupService.updateGroupNameService(names.get(0), names.get(1));
        return new ResponseEntity<>(groupe, HttpStatus.OK);
    }


    @DeleteMapping(value = "/groupe/delete/{name}", produces = {"application/json"})
    public ResponseEntity <String> deleteGroupe(@PathVariable String name) {
        //logger.debug("deleteGroupe({})", name);
        name = groupService.deleteGroupByGroupNameService(name);
        return new ResponseEntity(name, HttpStatus.OK);
    }



}
