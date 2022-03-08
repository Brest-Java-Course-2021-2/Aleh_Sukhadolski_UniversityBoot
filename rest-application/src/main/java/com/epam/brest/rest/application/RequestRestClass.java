package com.epam.brest.rest.application;

import com.epam.brest.Request;
import com.epam.brest.RequestDao;
import com.epam.brest.serviceapi.RequestServiceApi;
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
public class RequestRestClass {

    private final Logger logger = LogManager.getLogger(RequestRestClass.class);

    @Autowired
    RequestServiceApi requestService;

    @GetMapping ("/request/all/{id}")
    @Transactional(readOnly = true)
    public List<Request> getAllRequests(@PathVariable int id) {
        return (List<Request>) requestService.getAllRequestsService(id);
    }

    @GetMapping ("/request/{idr}")
    @Transactional(readOnly = true)
    public Request getRequestByIdr(@PathVariable int idr) {
        return (Request) requestService.getRequestByIdrService(idr);
    }

    @PutMapping(path = "/request/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Request> updateRequest(@RequestBody Request request) {
        //logger.debug("updateGroupe({})", newName);
        request= requestService.updateRequestService(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @DeleteMapping(value = "/request/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity <Request> deleteGroupe(@PathVariable Request request) {
        //logger.debug("deleteGroupe({})", name);
        requestService.flushRequestInfoService(request);
        return new ResponseEntity(request, HttpStatus.OK);
    }


}
