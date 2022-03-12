package com.epam.brest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Component
public interface LectorJpaRepository extends JpaRepository <Lector, Integer> {

    default List<Lector> findAllLectors() {
        List <Lector> lectors = findAll();
        if (lectors.size() == 0){
            lectors.add(new Lector("Is Empty","","",""));
        }
        return lectors;
    }

    default Lector findLectorByIdLector(Integer idLector) {
        return (Lector) findById(idLector).get();
    }


    default Lector findLectorByNameLector(String nameLector) {
        List<Lector> lectors = findAll().stream()
                .filter(lc -> lc.getNameLector().equals(nameLector))
                .collect(Collectors.toList());
        Lector lector;
        if (lectors.size() > 0){
            lector = lectors.get(0);
        } else {
            lector = new Lector(0,"Is Empty","","", "");
        }
        return (Lector) lector;
    }


    default Lector findLectorByEmailLector(String emailLector) {
        List<Lector> lectors = findAll().stream()
                .filter(lc -> lc.getEmailLector().equals(emailLector))
                .collect(Collectors.toList());
        Lector lector;
        if (lectors.size() > 0){
            lector = lectors.get(0);
        } else {
            lector = new Lector(0,"Is Empty","","", "");
        }
        return (Lector) lector;
    }


    default Lector saveAndFlushLector(Lector lector)
    {
        return (Lector) saveAndFlush(lector);
    }


    default void deleteLectorByIdLector(Integer idLector)
    {
        deleteById(idLector);
    }


    default void deleteLector(@NotNull Lector lector)
    {
        deleteById(lector.getIdLector());
    }

}
