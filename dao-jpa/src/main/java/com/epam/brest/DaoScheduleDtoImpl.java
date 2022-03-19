package com.epam.brest;

import com.epam.brest.dto.Schedule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ComponentScan ("com.epam.brest.*")
public class DaoScheduleDtoImpl implements DaoScheduleDtoApi {

    private final Logger logger = LogManager.getLogger(DaoScheduleDtoImpl.class);

    @Autowired
    private DaoGroupApi daoGroup;

    @Autowired
    private DaoLectorApi daoLector;

    @Autowired
    private DaoRequestFromLectorApi daoRequest;

    @Autowired
    private Schedule schedule;

    public List<DaySchedule> scheduleForAll = new ArrayList<>();
    public List<LectorsSchedule> lectorsSchedule = new ArrayList<>();
    public List<StudentsSchedule> studentsSchedule = new ArrayList<>();

    @Override
    public List<DaySchedule> createSchedule() {
        logger.info("Create schedule from the Lectors requests {}");

        List<String> groups = daoGroup.getAllGroupsNames();
        List<Lector> lectors = daoLector.getAllLectors();
        List<String> lectorNames = schedule.getLectorsNamesList(daoLector.getAllLectors());

        List<RequestsForGroupe> requestsForGroupes = new ArrayList<>();
        for (Lector lector : lectors) {
            List<RequestFromLector> listRequest = daoRequest.getAllRequestsFromLectorByIdLector(lector.getIdLector());
            for (RequestFromLector request : listRequest) {
                requestsForGroupes.add(new RequestsForGroupe(lector.getNameLector(), request.getGroup(), request.getSubjectOfLector(),
                                                             Integer.parseInt(request.getNumberOfPairs())));
            }
        }

        scheduleForAll = schedule.createSchedule(groups, lectorNames, requestsForGroupes);
        studentsSchedule = schedule.createScheduleForGroupe(groups);
        lectorsSchedule = schedule.createScheduleForLectors(lectorNames);

        return scheduleForAll;
    }

    @Override
    public List<LectorsSchedule> getScheduleForAllLectors() {
                logger.info("Get schedule to the all Lectors {}");
                return (List<LectorsSchedule>) schedule.lectorsScheduleForAll;
    }

    @Override
    public List<LectorsSchedule> getScheduleForLector(String lectorName) {
        logger.info("Get schedule for the Lector {}" + lectorName);
        return lectorsSchedule.stream()
                              .filter(lectorsSchedule -> lectorsSchedule.getLector().equals(lectorName))
                              .collect(Collectors.toList());
    }

    @Override
    public List<StudentsSchedule> getScheduleForAllStudents() {
        logger.info("Get schedule to the all groups {}");
        return (List<StudentsSchedule>) schedule.studentsScheduleForAll;
    }

    @Override
    public List<StudentsSchedule> getScheduleForGroup(String groupName) {
        logger.info("Get schedule for the group {}" + groupName);
        return studentsSchedule.stream()
                               .filter(studentsSchedule -> studentsSchedule.getGroupe().equals(groupName))
                               .collect(Collectors.toList());
    }
}
