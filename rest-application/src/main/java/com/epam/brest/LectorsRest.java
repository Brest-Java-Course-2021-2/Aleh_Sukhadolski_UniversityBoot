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

    @GetMapping("/user/all")
    @Transactional(readOnly = true)
    public List<Lector> users()
    {
        logger.debug("getAllUsers({})");
        return (List<Lector>) lectorService.getAllLectorsService();
    }


    @GetMapping("/user/name")
    @Transactional(readOnly = true)
    public Lector userByName(@RequestParam String name)
    {
        logger.debug("getUserByName({})", name);
        return (Lector) lectorService.getLectorByLectorsNameService(name);
    }

    @GetMapping("/user/email")
    @Transactional(readOnly = true)
    public Lector userByEmail(@RequestParam String email)
    {
        logger.debug("getUserByEmail({})", email);
        return (Lector) lectorService.getLectorByEmailService(email);
    }

    @GetMapping("/user/id")
    @Transactional(readOnly = true)
    public Lector userById(@RequestParam String id)
    {
        logger.debug("getUserById({})", id);
        return (Lector) lectorService.getLectorByIdLectorService(Integer.parseInt(id));
    }


    @PostMapping(path = "/user/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createUser(@RequestBody Lector user) {
        logger.debug("createUser({})", user);
        user = lectorService.createNewLectorService(user);
        return new ResponseEntity<>(user.getIdLector(), HttpStatus.OK);
    }


    @PutMapping(path = "/user/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> updateUser(@RequestBody Lector user) {
        logger.debug("updateUser({})", user);
        user = lectorService.createNewLectorService(user);
        return new ResponseEntity<>(user.getIdLector(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/delete/{id}", produces = {"application/json"})
    public ResponseEntity <Integer> deleteUserById(@PathVariable Integer id) {
        logger.debug("deleteUser({})", id);
        lectorService.deleteLectorByIdLectorService(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }


    @DeleteMapping(value = "/user/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity <Integer> deleteUser(@RequestBody Lector user) {
        logger.debug("deleteUser({})", user);
        lectorService.deleteLectorService(user);
        return new ResponseEntity(user.getIdLector(), HttpStatus.OK);
    }


}
