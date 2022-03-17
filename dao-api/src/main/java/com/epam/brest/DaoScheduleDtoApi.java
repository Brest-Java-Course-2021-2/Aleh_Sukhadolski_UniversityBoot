package com.epam.brest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ComponentScan("com.epam.brest")
public interface DaoScheduleDtoApi {

    public List<DaySchedule> createSchedule ();

    public List<LectorsSchedule> getScheduleForAllLectors();

    public List<LectorsSchedule> getScheduleForLector(String lectorName);

    public List<StudentsSchedule> getScheduleForAllStudents();

    public List<StudentsSchedule> getScheduleForGroup(String groupName);

}
