package com.epam.brest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ComponentScan("com.epam.brest")
public class LectorDao implements DaoLectorApi {

    private final Logger logger = LogManager.getLogger(LectorDao.class);

    @Autowired
    private LectorJpaRepository lectorRepository;


    @Override
    public List<Lector> findAllLectors() {
        logger.info("GET ALL LECTORS {}");
        return lectorRepository.findAllLectors();
    }

    @Override
    public Lector findLectorByIdLector(Integer idLector) {
        logger.info("GET LECTOR BY ID {}" + idLector);
        return lectorRepository.findLectorByIdLector(idLector);
    }

    @Override
    public Lector findLectorByNameLector(String nameLector) {
        logger.info("GET LECTOR BY Name Lector {}" + nameLector);
        return lectorRepository.findLectorByNameLector(nameLector);
    }

    @Override
    public Lector findLectorByEmailLector(String emailLector) {
        logger.info("GET LECTOR BY Email Lector {}" + emailLector);
        return lectorRepository.findLectorByEmailLector(emailLector);
    }

    @Override
    public Lector saveAndFlushLector(Lector lector) {
        logger.info("SAVE-UPDATE LECTOR {}" + lector);
        return lectorRepository.saveAndFlushLector(lector);
    }

    @Override
    public void deleteLectorByIdLector(Integer idLector) {
        logger.info("DELETE LECTOR BY Id Lector {}" + idLector);
        lectorRepository.deleteLectorByIdLector(idLector);
    }

    @Override
    public void deleteLector(Lector lector) {
        logger.info("DELETE LECTOR as Lector {}" + lector);
        lectorRepository.deleteLector(lector);
    }
}
