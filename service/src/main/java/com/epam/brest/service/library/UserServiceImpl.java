package com.epam.brest.service.library;


import com.epam.brest.daoAPI.DaoGroupeApi;
import com.epam.brest.daoAPI.DaoRequestApi;
import com.epam.brest.daoAPI.DaoUserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    private DaoUserApi daoUser;

    @Autowired
    private DaoRequestApi daoRequest;

    @Autowired
    private DaoGroupeApi daoGroupe;

}
