package com.epam.brest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@ComponentScan("com.epam.brest")
public class DaoLectorImpl implements DaoLectorApi {

    private final Logger logger = LogManager.getLogger(DaoLectorImpl.class);

    @Autowired
    private LectorsJpaRepository userRepository;

    public List<Lector> getAllLectors() {
        logger.info("GET ALL USERS {}");
        return (List<Lector>) userRepository.findAllLectors();
    }


    public Lector getLectorByName(String name) {
        logger.info("GET USER BY NAME{} " + name);
        return userRepository.findLectorByLectorsName(name);
    }

    public Lector getLectorByEmail(String email) {
        logger.info("GET USER BY EMAIL{} " + email);
        return userRepository.findLectorByEmail(email);
    }

    public Lector getLectorById(Integer id) {
        logger.info("GET USER BY ID{} " + id);
        return (Lector) userRepository.findLectorByLectorsId(id);
    }

    public Lector saveOrUpdateLector(Lector user) {
        logger.info("SAVE USER {} " + user);
        return (Lector) userRepository.saveOrUpdateLector(user);
    }

    public void deleteLector(Lector user) {
        logger.info("DELETE USER {}" + user);
        userRepository.deleteLectorByLectorsId(user.getIdLector());
    }

    public void deleteLectorById(Integer id) {
        logger.info("DELETE USER id = " + id );
        userRepository.deleteLectorByLectorsId(id);
    }


}
