package com.epam.brest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ComponentScan("com.epam.brest")
public class LectorDao implements DaoLectorApi {

    @Autowired
    private LectorJpaRepository lectorRepository;


    @Override
    public List<Lector> findAllLectors() {
        return null;
    }

    @Override
    public Lector findLectorByIdLector(Integer idLector) {
        return null;
    }

    @Override
    public Lector findLectorByNameLector(String nameLector) {
        return null;
    }

    @Override
    public Lector findLectorByEmailLector(String emailLector) {
        return null;
    }

    @Override
    public Lector saveAndFlushLector(Lector lector) {
        return null;
    }

    @Override
    public void deleteLectorByIdLector(Integer idLector) {

    }

    @Override
    public void deleteLector(Lector lector) {

    }
}
