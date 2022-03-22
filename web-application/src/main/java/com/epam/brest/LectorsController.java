package com.epam.brest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LectorsController {

    private static final Logger logger = LoggerFactory.getLogger(LectorsController.class);

    @Autowired
    private LectorServiceApi lectorService;



    @GetMapping(value = "/lectors")
    public final String lectors (Model model) {
        model.addAttribute("lectors", lectorService.getAllLectorsService());
        return "lectors";
    }
}
