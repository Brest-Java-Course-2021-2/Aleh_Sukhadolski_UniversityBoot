package com.epam.brest;

import com.epam.brest.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Component
public interface UserRepository extends JpaRepository<User, Integer> {

    default List<User> findAllUsers() {
        List <User> users = findAll();
        if (users.size() == 0){
            users.add(new User("Is Empty","","",""));
        }
        return findAll();
    }

    default User findUserById(Integer id) {
        return (User) findById(id).get();
    }

    default User findUserByName(String name) {
        List<User> users = findAll().stream()
                .filter(us -> us.getName().equals(name))
                .collect(Collectors.toList());
        User user;
          if (users.size() > 0){
            user = users.get(0);
        } else {
            user = new User(0,"Is Empty","","", "");
        }
        return (User) user;
    }


    default User findUserByEmail(String email) {
        List<User> users = findAll().stream()
                .filter(us -> us.getEmail().equals(email))
                .collect(Collectors.toList());
        User user;
        if (users.size() > 0){
            user = users.get(0);
        } else {
            user = new User(0,"Is Empty","","", "");
        }
        return (User) user;
    }

    default User saveAndFlushUser(User user) {
        return (User) saveAndFlush(user);
    }

    default void deleteUserById(Integer id) {
        deleteById(id);
    }

    default void deleteUser(User user) {
        deleteById(user.getId());
    }
}
