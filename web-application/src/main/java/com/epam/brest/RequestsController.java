package com.epam.brest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RequestsController {
    private static final Logger logger = LoggerFactory.getLogger(RequestsController.class);

    @Autowired
    private RequestFromLectorServiceApi requestFromLectorService;

    @GetMapping(value = "/lector/requests/{id}")
    public final String lectorsRequests (@PathVariable Integer id, Model model) {
        model.addAttribute("requests",
                            requestFromLectorService.getAllRequestsFromLectorService(id));
        return "lectorsrequest";
    }

}
