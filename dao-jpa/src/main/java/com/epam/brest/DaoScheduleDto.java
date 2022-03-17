package com.epam.brest;

import com.epam.brest.dto.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan ("com.epam.brest.*")
public class DaoScheduleDto implements DaoScheduleDtoApi {

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
                requestsForGroupes.add(new RequestsForGroupe(
                        lector.getNameLector()
                        , request.getGroup()
                        , request.getSubjectOfLector()
                        , Integer.parseInt(request.getNumberOfPairs())));
            }
        }

        List<DaySchedule> scheduleList = schedule.createSchedule(groups, lectorNames, requestsForGroupes);


    }

    @Override
    public List<LectorsSchedule> getScheduleForAllLectors() {
        return null;
    }

    @Override
    public List<LectorsSchedule> getScheduleForLector(String lectorName) {
        return null;
    }

    @Override
    public List<StudentsSchedule> getScheduleForAllStudents() {
        return null;
    }

    @Override
    public List<StudentsSchedule> getScheduleForGroup(String groupName) {
        return null;
    }
}
