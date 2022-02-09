package com.epam.brest.rest.application;

import com.epam.brest.service.library.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceRestClass {

    private final MyService myService;

    public ServiceRestClass(MyService myService) {
        this.myService = myService;
    }


    @GetMapping("/hello")
    public String home() {
        return myService.message();
    }

    @GetMapping("/version")
    public String appversion() {
        return "version-0.0.1-SNAPSHOT";
    }
}
