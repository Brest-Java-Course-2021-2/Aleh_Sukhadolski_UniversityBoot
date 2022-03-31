package com.epam.brest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ScheduleRest {

    private final Logger logger = LogManager.getLogger(ScheduleRest.class);

    @Autowired
    ScheduleDtoServiceApi scheduleDtoService;

    @GetMapping("/schedule/create")
    @Transactional(readOnly = true)
    public Integer createSchedule() {
        logger.debug("Create schedule({})");
        return (Integer) scheduleDtoService.createScheduleService();
    }

    @GetMapping("/schedule/alllectors")
    @Transactional(readOnly = true)
    public List<List<LectorsSchedule>> getScheduleForAllLectors() {
        logger.debug("Get schedule for all lectors({})");
        return (List<List<LectorsSchedule>>) scheduleDtoService.getScheduleForAllLectorsService();
    }

    @GetMapping("/schedule/allgroups")
    @Transactional(readOnly = true)
    public List<List<StudentsSchedule>> getScheduleForAllGroups() {
        logger.debug("Get schedule for all groups({})");
        return (List<List<StudentsSchedule>>) scheduleDtoService.getScheduleForAllGroupsService();
    }

    @GetMapping("/schedule/{id}")
    @Transactional(readOnly = true)
    public List<LectorsSchedule> getScheduleForLector(@RequestParam Integer id) {
        logger.debug("Get schedule for lector({})" + id);
        return (List<LectorsSchedule>) scheduleDtoService.getScheduleForLectorService(id);
    }

    @GetMapping("/schedule/{id}")
    @Transactional(readOnly = true)
    public List<StudentsSchedule> getScheduleForGroup(@RequestParam Integer id) {
        logger.debug("Get schedule for group({})" + id);
        return (List<StudentsSchedule>) scheduleDtoService.getScheduleForGroupService(id);
    }

}
