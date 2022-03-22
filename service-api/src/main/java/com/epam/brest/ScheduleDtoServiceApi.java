package com.epam.brest;

import com.epam.brest.LectorsSchedule;
import com.epam.brest.StudentsSchedule;

import java.util.List;

public interface ScheduleDtoServiceApi {

    public Integer createScheduleService ();

    public List<LectorsSchedule> getScheduleForAllLectorsService();

    public List<LectorsSchedule> getScheduleForLectorService(String lectorName);

    public List<StudentsSchedule> getScheduleForAllGroupsService();

    public List<StudentsSchedule> getScheduleForGroupService(String groupName);

}
