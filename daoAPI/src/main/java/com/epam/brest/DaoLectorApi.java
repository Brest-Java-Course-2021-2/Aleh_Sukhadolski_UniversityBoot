package com.epam.brest;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@EntityScan("com.epam.brest")
public interface DaoLectorApi {

    public List<Lector> findAllLectors();

    public Lector findLectorByIdLector(Integer idLector);

    public Lector findLectorByNameLector(String nameLector);

    public Lector findLectorByEmailLector(String emailLector);

    public Lector saveAndFlushLector(Lector lector);

    public void deleteLectorByIdLector(Integer idLector);

    public void deleteLector(Lector lector);

}
