package com.epam.brest.serviceapi;

import com.epam.brest.Lector;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest.model")
public interface LectorServiceApi {

    List<Lector> getAllLectorsService();

    Lector getLectorByLectorsNameService(String name);

    Lector getLectorByEmailService(String email);

    Lector getLectorByIdLectorService(Integer id);

    Lector createNewLectorService(Lector user);

    Lector updateLectorService(Lector user);

    void deleteLectorService(Lector user);

    void deleteLectorByIdLectorService(Integer id);

}
