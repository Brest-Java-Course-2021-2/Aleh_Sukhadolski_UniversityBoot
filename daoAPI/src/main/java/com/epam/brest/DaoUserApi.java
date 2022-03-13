package com.epam.brest;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest")
public interface DaoUserApi {


    public List<Lector> getAllUsers();

    public Lector getUserByName(String name);

    public Lector getUserByEmail(String email);

    public Lector getUserById(Integer id);

    public Lector saveAndUpdateUser(Lector user);

    public void deleteUser(Lector user);

    public void deleteUserById(Integer id);

}
