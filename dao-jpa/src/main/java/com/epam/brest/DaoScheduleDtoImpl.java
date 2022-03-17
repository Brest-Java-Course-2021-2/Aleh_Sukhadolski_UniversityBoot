package com.epam.brest;

import com.epam.brest.dto.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ComponentScan ("com.epam.brest.*")
public class DaoScheduleDtoImpl implements DaoScheduleDtoApi {

    @Autowired
    private DaoGroupApi daoGroup;

    @Autowired
    private DaoLectorApi daoLector;

    @Autowired
    private DaoRequestFromLectorApi daoRequest;

    @Autowired
    private Schedule schedule;

    public List<DaySchedule> scheduleForAll;
    public List<LectorsSchedule> lectorsSchedule;
    public List<StudentsSchedule> studentsSchedule;


    @Override
    public List<DaySchedule> createSchedule() {
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
                return (List<LectorsSchedule>) schedule.lectorsScheduleForAll;
    }

    @Override
    public List<LectorsSchedule> getScheduleForLector(String lectorName) {
               return lectorsSchedule.stream()
                                     .filter(lectorsSchedule -> lectorsSchedule.getLector().equals(lectorName))
                                     .collect(Collectors.toList());
    }

    @Override
    public List<StudentsSchedule> getScheduleForAllStudents() {
        return (List<StudentsSchedule>) schedule.studentsScheduleForAll;
    }

    @Override
    public List<StudentsSchedule> getScheduleForGroup(String groupName) {
        return studentsSchedule.stream()
                               .filter(studentsSchedule -> studentsSchedule.getGroupe().equals(groupName))
                               .collect(Collectors.toList());
    }
}
