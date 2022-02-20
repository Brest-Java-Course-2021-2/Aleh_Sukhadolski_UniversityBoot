package com.epam.brest.dao.jparepositories;

import com.epam.brest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.stream.Collectors;



// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Integer> {

    default List<User> findAllUsers() {
        return findAll();
    }

    default User findUserById(Integer id) {
        return (User) findById(id).get();
    }

    default List<User> findUserByName(String name) {
        return (List<User>) findAll().stream()
                .filter(user -> user.getName().equals(name))
                .collect(Collectors.toList());
    }

    default List<User> findUserByEmail(String email) {
        return (List<User>) findAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .collect(Collectors.toList());
    }

    default User saveAndFlushUser(User user) {
        return (User) saveAndFlush(user);
    }

    default void deleteUserById(Integer id) {
        deleteById(id);
    }

    default void deleteUser(User user) { deleteById(user.getId()); }
}
