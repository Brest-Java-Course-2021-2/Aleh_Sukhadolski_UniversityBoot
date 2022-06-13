package com.epam.brest.kafkaweb.consumer;

import com.epam.brest.Lector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
@EnableJpaRepositories(basePackages = "com.epam.brest")
public class LectorConsumerServiceWeb {

    private static final Logger logger = LoggerFactory.getLogger(LectorConsumerServiceWeb.class);

    @Autowired
    private final ConsumerFactory<String, String> stringKafkaTemplate;
    @Autowired
    private final ConsumerFactory<String, Lector> lectorKafkaTemplate;
    @Autowired
    private final ConsumerFactory<String, List<Lector>> listLectorKafkaTemplate;


    public List<Lector> lectors = new ArrayList<>();

    public Boolean isListLectorChanged = false;
    public Boolean isLectorChanged = false;
    public Boolean isLectorCreated = false;
    public Boolean isLectorUpdated = false;
    public Boolean isLectorDeleted = false;

    public LectorConsumerServiceWeb(ConsumerFactory<String, String> stringKafkaTemplate
            , ConsumerFactory<String, Lector> lectorKafkaTemplate
            , ConsumerFactory<String, List<Lector>> listLectorKafkaTemplate) {

        this.stringKafkaTemplate = stringKafkaTemplate;
        this.lectorKafkaTemplate = lectorKafkaTemplate;
        this.listLectorKafkaTemplate = listLectorKafkaTemplate;
    }
}
