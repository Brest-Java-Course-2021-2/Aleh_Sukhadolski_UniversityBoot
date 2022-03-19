package com.epam.brest;

import com.epam.brest.serviceapi.ScheduleDtoServiceApi;
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
    public List<LectorsSchedule> getScheduleForAllLectors() {
        logger.debug("Get schedule for all lectors({})");
        return (List<LectorsSchedule>) scheduleDtoService.getScheduleForAllLectorsService();
    }

    @GetMapping("/schedule/allgroups")
    @Transactional(readOnly = true)
    public List<StudentsSchedule> getScheduleForAllGroups() {
        logger.debug("Get schedule for all groups({})");
        return (List<StudentsSchedule>) scheduleDtoService.getScheduleForAllGroupsService();
    }

    @GetMapping("/schedule/lector")
    @Transactional(readOnly = true)
    public List<LectorsSchedule> getScheduleForLector(@RequestParam String lectorName) {
        logger.debug("Get schedule for lector({})" + lectorName);
        return (List<LectorsSchedule>) scheduleDtoService.getScheduleForLectorService(lectorName);
    }

    @GetMapping("/schedule/group")
    @Transactional(readOnly = true)
    public List<StudentsSchedule> getScheduleForGroup(@RequestParam String groupName) {
        logger.debug("Get schedule for group({})" + groupName);
        return (List<StudentsSchedule>) scheduleDtoService.getScheduleForGroupService(groupName);
    }

}
