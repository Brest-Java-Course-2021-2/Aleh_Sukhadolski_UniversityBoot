package com.epam.brest;

import com.epam.brest.Lector;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

public interface LectorServiceApi {

    List<Lector> getAllLectorsService();

    Lector getLectorByLectorsNameService(String name);

    Lector getLectorByEmailService(String email);

    Lector getLectorByIdLectorService(Integer id);

    Lector createNewLectorService(Lector lector);

    Lector updateLectorService(Lector lector);

    Integer deleteLectorByIdLectorService(Integer id);

}
