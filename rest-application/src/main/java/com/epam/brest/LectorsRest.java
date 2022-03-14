package com.epam.brest;

import com.epam.brest.serviceapi.LectorServiceApi;
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
public class LectorsRest {

    private final Logger logger = LogManager.getLogger(LectorsRest.class);

    @Autowired
    private LectorServiceApi lectorService;

    @GetMapping("/lector/all")
    @Transactional(readOnly = true)
    public List<Lector> users()
    {
        logger.debug("getAllLectors({})");
        return (List<Lector>) lectorService.getAllLectorsService();
    }


    @GetMapping("/lector/name")
    @Transactional(readOnly = true)
    public Lector userByName(@RequestParam String name)
    {
        logger.debug("getLectorByName({})", name);
        return (Lector) lectorService.getLectorByLectorsNameService(name);
    }

    @GetMapping("/lector/email")
    @Transactional(readOnly = true)
    public Lector userByEmail(@RequestParam String email)
    {
        logger.debug("getLectorByEmail({})", email);
        return (Lector) lectorService.getLectorByEmailService(email);
    }

    @GetMapping("/lector/id")
    @Transactional(readOnly = true)
    public Lector userById(@RequestParam String id)
    {
        logger.debug("getLectorById({})", id);
        return (Lector) lectorService.getLectorByIdLectorService(Integer.parseInt(id));
    }


    @PostMapping(path = "/lector/new", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createUser(@RequestBody Lector user) {
        logger.debug("createLector({})", user);
        user = lectorService.createNewLectorService(user);
        return new ResponseEntity<>(user.getIdLector(), HttpStatus.OK);
    }


    @PutMapping(path = "/lector/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> updateUser(@RequestBody Lector user) {
        logger.debug("updateLector({})", user);
        user = lectorService.createNewLectorService(user);
        return new ResponseEntity<>(user.getIdLector(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/lector/delete/{id}", produces = {"application/json"})
    public ResponseEntity <Integer> deleteUserById(@PathVariable Integer id) {
        logger.debug("deleteLector by id({})", id);
        lectorService.deleteLectorByIdLectorService(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }


    @DeleteMapping(value = "/lector/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity <Integer> deleteUser(@RequestBody Lector user) {
        logger.debug("deleteLector({})", user);
        lectorService.deleteLectorService(user);
        return new ResponseEntity(user.getIdLector(), HttpStatus.OK);
    }


}
