package com.epam.brest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Component
public interface LectorsJpaRepository extends JpaRepository<Lector, Integer> {


    default List<Lector> findAllLectors() {
        List <Lector> users = findAll();
        if (users.size() == 0){
            users.add(new Lector("Is Empty","","",""));
        }
        return findAll();
    }


    default Lector findLectorByLectorsId(Integer id) {
        return (Lector) findById(id).get();
    }


    default Lector findLectorByLectorsName(String name) {
        List<Lector> users = findAll().stream()
                .filter(us -> us.getNameLector().equals(name))
                .collect(Collectors.toList());
        Lector user;
          if (users.size() > 0){
            user = users.get(0);
        } else {
            user = new Lector(0,"Is Empty","","", "");
        }
        return (Lector) user;
    }


    default Lector findLectorByEmail(String email) {
        List<Lector> users = findAll().stream()
                .filter(us -> us.getEmailLector().equals(email))
                .collect(Collectors.toList());
        Lector user;
        if (users.size() > 0){
            user = users.get(0);
        } else {
            user = new Lector(0,"Is Empty","","", "");
        }
        return (Lector) user;
    }


    default Lector saveOrUpdateLector(Lector user) {
        return (Lector) saveAndFlush(user);
    }


    default void deleteLectorByLectorsId(Integer id) {
        deleteById(id);
    }


    default void deleteLector(@NotNull Lector user) {
        deleteById(user.getIdLector());
    }
}
