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

    @GetMapping("/lectors/get-all")
    @Transactional(readOnly = true)
    public List<Lector> users()
    {
        logger.debug("getAllLectors({})");
        return (List<Lector>) lectorService.getAllLectorsService();
    }


    @GetMapping("/lectors/lector/get-name")
    @Transactional(readOnly = true)
    public Lector userByName(@RequestParam String name)
    {
        logger.debug("getLectorByName({})", name);
        return (Lector) lectorService.getLectorByLectorsNameService(name);
    }

    @GetMapping("/lectors/lector/get-email")
    @Transactional(readOnly = true)
    public Lector userByEmail(@RequestParam String email)
    {
        logger.debug("getLectorByEmail({})", email);
        return (Lector) lectorService.getLectorByEmailService(email);
    }

    @GetMapping("/lectors/lector/{id}")
    @Transactional(readOnly = true)
    public Lector userById(@PathVariable Integer id)
    {
        logger.debug("getLectorById({})", id);
        return (Lector) lectorService.getLectorByIdLectorService(id);
    }


    @PostMapping(path = "/lectors/lector/new", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createUser(@RequestBody Lector lector) {
        logger.debug("createLector({})", lector);
        lector = lectorService.createNewLectorService(lector);
        return new ResponseEntity<>(lector.getIdLector(), HttpStatus.OK);
    }


    @PutMapping(path = "/lectors/lector/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> updateUser(@RequestBody Lector lector) {
        logger.debug("updateLector({})", lector);
        lector = lectorService.createNewLectorService(lector);
        return new ResponseEntity<>(lector.getIdLector(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/lectors/lector/{id}/delete", produces = {"application/json"})
    public ResponseEntity <Integer> deleteUserById(@PathVariable Integer id) {
        logger.debug("deleteLector by id({})", id);
        lectorService.deleteLectorByIdLectorService(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }


    @DeleteMapping(value = "/lectors/lector/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity <Integer> deleteUser(@RequestBody Lector lector) {
        logger.debug("deleteLector({})", lector);
        lectorService.deleteLectorService(lector);
        return new ResponseEntity(lector.getIdLector(), HttpStatus.OK);
    }

}
