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

    @Override
    public void createSchedule() {
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

        schedule.createSchedule(groups, lectorNames, requestsForGroupes);
        schedule.createScheduleForGroupe(groups);
        schedule.createScheduleForLectors(lectorNames);

    }

    @Override
    public List<LectorsSchedule> getScheduleForAllLectors() {
                return (List<LectorsSchedule>) schedule.lectorsScheduleForAll;
    }

    @Override
    public List<LectorsSchedule> getScheduleForLector(String lectorName) {
               return schedule.lectorsScheduleForAll.stream()
                                .filter(lectorsSchedule -> lectorsSchedule.getLector().equals(lectorName))
                                .collect(Collectors.toList());
    }

    @Override
    public List<StudentsSchedule> getScheduleForAllStudents() {
        return (List<StudentsSchedule>) schedule.studentsScheduleForAll;
    }

    @Override
    public List<StudentsSchedule> getScheduleForGroup(String groupName) {
        return schedule.studentsScheduleForAll.stream()
                .filter(studentsSchedule -> studentsSchedule.getGroupe().equals(groupName))
                .collect(Collectors.toList());
    }
}
