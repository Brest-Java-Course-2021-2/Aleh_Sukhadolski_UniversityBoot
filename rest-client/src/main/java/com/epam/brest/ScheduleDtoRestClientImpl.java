package com.epam.brest;

import com.epam.brest.serviceapi.ScheduleDtoServiceApi;

import java.util.List;

public class ScheduleDtoRestClientImpl implements ScheduleDtoServiceApi {

    @Override
    public Integer createScheduleService() {
        return null;
    }

    @Override
    public List<LectorsSchedule> getScheduleForAllLectorsService() {
        return null;
    }

    @Override
    public List<LectorsSchedule> getScheduleForLectorService(String lectorName) {
        return null;
    }

    @Override
    public List<StudentsSchedule> getScheduleForAllGroupsService() {
        return null;
    }

    @Override
    public List<StudentsSchedule> getScheduleForGroupService(String groupName) {
        return null;
    }
}
