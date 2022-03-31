package com.epam.brest;

import java.util.List;

public interface DaoScheduleDtoApi {

    public List<DaySchedule> createSchedule ();

    public List<List<LectorsSchedule>> getScheduleForAllLectors();

    public List<LectorsSchedule> getScheduleForLector(Integer idLector);

    public List<List<StudentsSchedule>> getScheduleForAllStudents();

    public List<StudentsSchedule> getScheduleForGroup(Integer idGroup);

}
