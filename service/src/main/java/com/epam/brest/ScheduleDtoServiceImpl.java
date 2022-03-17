package com.epam.brest;

import com.epam.brest.serviceapi.ScheduleDtoServiceApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@ComponentScan("com.epam.brest")
@Service
public class ScheduleDtoServiceImpl implements ScheduleDtoServiceApi {

    private final Logger logger = LogManager.getLogger(ScheduleDtoServiceImpl.class);

    @Autowired
    DaoScheduleDtoApi daoScheduleDto;

    @Override
    public Integer createScheduleService() {
        return (Integer) daoScheduleDto.createSchedule().size();
    }

    @Override
    public List<LectorsSchedule> getScheduleForAllLectorsService() {
        return (List<LectorsSchedule>) daoScheduleDto.getScheduleForAllLectors();
    }

    @Override
    public List<LectorsSchedule> getScheduleForLectorService(String lectorName) {
        return (List<LectorsSchedule>) daoScheduleDto.getScheduleForLector(lectorName);
    }

    @Override
    public List<StudentsSchedule> getScheduleForAllGroupsService() {
        return (List<StudentsSchedule>) daoScheduleDto.getScheduleForAllStudents();
    }

    @Override
    public List<StudentsSchedule> getScheduleForGroupService(String groupName) {
        return (List<StudentsSchedule>) daoScheduleDto.getScheduleForGroup(groupName);
    }
}
