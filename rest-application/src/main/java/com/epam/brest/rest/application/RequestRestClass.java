package com.epam.brest.rest.application;

import com.epam.brest.Request;
import com.epam.brest.serviceapi.RequestServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class RequestRestClass {

    @Autowired
    RequestServiceApi requestService;

    @GetMapping ("/request/id/all")
    @Transactional(readOnly = true)
    public List<Request> getAllRequests(@RequestParam String id) {
        return (List<Request>) requestService.getAllRequestsService(Integer.parseInt(id));
    }
    @GetMapping ("/request/idr")
    @Transactional(readOnly = true)
    public Request getRequestByIdr(@RequestParam String idR) {
        return (Request) requestService.getRequestByIdrService(Integer.parseInt(idR));
    }

    @GetMapping ("/request/update")
    public Request updateRequest(@RequestParam String idR, @RequestParam String id
                               , @RequestParam String groupe, @RequestParam String pairs
                               , @RequestParam String subject) throws ParseException {
        Request request = new Request(Integer.parseInt(idR), Integer.parseInt(id)
                                    , groupe, pairs, subject, new Date());
        return (Request) requestService.updateRequestService(request);
    }


}
