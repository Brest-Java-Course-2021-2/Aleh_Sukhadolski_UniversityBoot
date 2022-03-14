package com.epam.brest;

import com.epam.brest.serviceapi.LectorServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ServiceUniversityInfoRest {
/*

    @Autowired
    private LectorServiceApi lectorService;

*/
    @GetMapping("/version")
    public String appversion() {
        return "version-0.0.1-SNAPSHOT";
    }

}