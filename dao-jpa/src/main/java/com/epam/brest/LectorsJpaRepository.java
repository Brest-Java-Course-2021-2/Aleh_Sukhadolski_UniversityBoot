package com.epam.brest;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface LectorsJpaRepository extends JpaRepository<Lector, Integer> {

    default List<Lector> findAllLectors() {
        List <Lector> lectors = findAll();
        if (lectors.size() == 0){
            lectors.add(new Lector("Is Empty","","",""));
        }
        return findAll();
    }


    default Lector findLectorByLectorsId(Integer id) {
        return (Lector) findById(id).get();
    }


    default Lector findLectorByLectorsName(String name) {
        List<Lector> lectors = findAll().stream()
                .filter(us -> us.getNameLector().equals(name))
                .collect(Collectors.toList());
        Lector lector;
          if (lectors.size() > 0){
            lector = lectors.get(0);
        } else {
            lector = new Lector(0,"Is Empty","","", "");
        }
        return (Lector) lector;
    }


    default Lector findLectorByEmail(String email) {
        List<Lector> lectors = findAll().stream()
                .filter(us -> us.getEmailLector().equals(email))
                .collect(Collectors.toList());
        Lector lector;
        if (lectors.size() > 0){
            lector = lectors.get(0);
        } else {
            lector = new Lector(0,"Is Empty","","", "");
        }
        return (Lector) lector;
    }


    default Lector saveOrUpdateLector(Lector lector) {
        return (Lector) saveAndFlush(lector);
    }


    default void deleteLectorByLectorsId(Integer id) {
        deleteById(id);
    }


    default void deleteLector(@NotNull Lector lector) {
        deleteById(lector.getIdLector());
    }
}
