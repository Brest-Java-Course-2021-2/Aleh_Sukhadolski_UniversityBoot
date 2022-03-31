package com.epam.brest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ScheduleController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleDtoServiceApi scheduleDtoService;

    @GetMapping(value = "/schedule")
    public final String groups (Model model) {
        List<List<StudentsSchedule>> studentsSchedule = scheduleDtoService.getScheduleForAllGroupsService();
        model.addAttribute("studentsSchedule", scheduleDtoService.getScheduleForAllGroupsService());
        return "allgroupsschedule";
    }

    @GetMapping(value = "/createschedule")
    public final String schedule () {
        scheduleDtoService.createScheduleService();
        return "redirect:/lectors";
    }

}
