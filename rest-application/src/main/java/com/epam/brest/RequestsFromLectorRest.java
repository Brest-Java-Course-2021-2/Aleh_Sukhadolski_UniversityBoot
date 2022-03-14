package com.epam.brest;

import com.epam.brest.serviceapi.RequestFromLectorServiceApi;
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
public class RequestsFromLectorRest {

    private final Logger logger = LogManager.getLogger(RequestsFromLectorRest.class);

    @Autowired
    RequestFromLectorServiceApi requestFromLectorService;

    @GetMapping ("/request/all/{id}")
    @Transactional(readOnly = true)
    public List<RequestFromLector> getAllRequests(@PathVariable int id) {
        logger.debug("get all requests from lector where foreign key id = ", id);
        return (List<RequestFromLector>) requestFromLectorService.getAllRequestsFromLectorService(id);
    }

    @GetMapping ("/request/{idr}")
    @Transactional(readOnly = true)
    public RequestFromLector getRequestByIdr(@PathVariable int idr) {
        logger.debug("get request from lector where id = ", idr);
        return (RequestFromLector) requestFromLectorService.getRequestOfLectorByIdRequestService(idr);
    }

    @PutMapping(path = "/request/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RequestFromLector> updateRequest(@RequestBody RequestFromLector request) {
        logger.debug("update request from lector ", request);
        request= requestFromLectorService.updateRequestFromLectorService(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @DeleteMapping(value = "/request/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity <RequestFromLector> deleteGroupe(@PathVariable RequestFromLector request) {
        logger.debug("delete request from lector = ", request);
        requestFromLectorService.flushRequestFromLectorService(request);
        return new ResponseEntity(request, HttpStatus.OK);
    }


}
