package com.epam.brest.kafkarest.consumer;

import com.epam.brest.LectorsSchedule;
import com.epam.brest.ScheduleDtoServiceApi;
import com.epam.brest.StudentsSchedule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@EnableJpaRepositories(basePackages = "com.epam.brest")
public class ScheduleKafkaConsumerServiceRest {

    private final Logger logger = LogManager.getLogger(ScheduleKafkaConsumerServiceRest.class);

    @Autowired
    private final ConsumerFactory<String, String> stringKafkaTemplate;

    @Autowired
    private final ConsumerFactory<String, List<StudentsSchedule>> listStudentsScheduleConsumerFactory;

    @Autowired
    private final ConsumerFactory<String, List<LectorsSchedule>> listLectorsScheduleConsumerFactory;

    @Autowired
    private final ConsumerFactory<String, List<List<StudentsSchedule>>> listAllStudentsScheduleConsumerFactory;

    @Autowired
    private final ConsumerFactory<String, List<List<LectorsSchedule>>> listAllLectorsScheduleConsumerFactory;

    @Autowired
    private final ScheduleDtoServiceApi scheduleDtoService;

    public ScheduleKafkaConsumerServiceRest(ConsumerFactory<String, String> stringKafkaTemplate
                                , ConsumerFactory<String, List<StudentsSchedule>> listStudentsScheduleConsumerFactory
                                , ConsumerFactory<String, List<LectorsSchedule>> listLectorsScheduleConsumerFactory
                                , ConsumerFactory<String, List<List<StudentsSchedule>>> listAllStudentsScheduleConsumerFactory
                                , ConsumerFactory<String, List<List<LectorsSchedule>>> listAllLectorsScheduleConsumerFactory
                                , ScheduleDtoServiceApi scheduleDtoService) {

        this.stringKafkaTemplate = stringKafkaTemplate;
        this.listStudentsScheduleConsumerFactory = listStudentsScheduleConsumerFactory;
        this.listLectorsScheduleConsumerFactory = listLectorsScheduleConsumerFactory;
        this.listAllStudentsScheduleConsumerFactory = listAllStudentsScheduleConsumerFactory;
        this.listAllLectorsScheduleConsumerFactory = listAllLectorsScheduleConsumerFactory;
        this.scheduleDtoService = scheduleDtoService;
    }


}
