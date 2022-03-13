package com.epam.brest.serviceapi;

import com.epam.brest.Lector;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest.model")
public interface UserServiceApi {

    List<Lector> getAllUsersService();

    Lector getUserByNameService(String name);

    Lector getUserByEmailService(String email);

    Lector getUserByIdService(Integer id);

    Lector saveNewUserService(Lector user);

    Lector updateUserService (Lector user);

    void deleteUserService(Lector user);

    void deleteUserByIdService(Integer id);

}
