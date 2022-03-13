package com.epam.brest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Component
public interface UserJpaRepository extends JpaRepository<Lector, Integer> {


    default List<Lector> findAllUsers() {
        List <Lector> users = findAll();
        if (users.size() == 0){
            users.add(new Lector("Is Empty","","",""));
        }
        return findAll();
    }


    default Lector findUserById(Integer id) {
        return (Lector) findById(id).get();
    }


    default Lector findUserByName(String name) {
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


    default Lector findUserByEmail(String email) {
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


    default Lector saveAndFlushUser(Lector user) {
        return (Lector) saveAndFlush(user);
    }


    default void deleteUserById(Integer id) {
        deleteById(id);
    }


    default void deleteUser(@NotNull Lector user) {
        deleteById(user.getIdLector());
    }
}
