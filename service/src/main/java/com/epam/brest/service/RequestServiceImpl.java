package com.epam.brest.service;

import com.epam.brest.RequestDao;
import com.epam.brest.daoAPI.DaoGroupeApi;
import com.epam.brest.daoAPI.DaoRequestApi;
import com.epam.brest.daoAPI.DaoUserApi;
import com.epam.brest.Request;
import com.epam.brest.User;
import com.epam.brest.serviceapi.RequestServiceApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Component
@ComponentScan("com.epam.brest")
@EntityScan("com.epam.brest")
@Service
public class RequestServiceImpl implements RequestServiceApi {

    private final Logger logger = LogManager.getLogger(RequestServiceImpl.class);

    @Autowired
    private DaoRequestApi daoRequest;

    @Autowired
    private DaoGroupeApi daoGroupe;

    @Autowired
    private DaoUserApi daoUser;

    @Override
    public List<Request> getAllRequestsService(Integer id) {
        return (List<Request>) daoRequest.getAllRequests(id);
    }

    @Override
    public Request getRequestByIdrService(Integer idR) {
        return (Request) daoRequest.getRequestByIdr(idR);
    }

    @Override
    public List<Request> saveRequestsForNewUserService(Integer id) {
        List<String> groupes = daoGroupe.getAllGroupeNames();
        return (List<Request>) daoRequest.saveRequestsForNewUser(id, groupes);
    }

    @Override
    public List<Request> saveRequestsWhenNewGroupeService(String groupe) {
        List<User> users = daoUser.getAllUsers();
        List<Integer> ids = new ArrayList<>();
        for (User u : users) { ids.add(u.getId()); }
        daoGroupe.insertNewGroupe(groupe);
        return (List<Request>) daoRequest.saveRequestsWhenNewGroupe(groupe, ids);
    }

    @Override
    public Request updateRequestService(Request request) {
        return (Request) daoRequest.updateRequest(request);
    }

    @Override
    public List<Request> updateAllRequestsForUserService(List<Request> requests) {
        return (List<Request>) daoRequest.updateAllRequestsForUser(requests);
    }

    @Override
    public Request flushRequestInfoService(Request request) {
        return (Request) daoRequest.flushRequestInfo(request);
    }

    @Override
    public void deleteAllRequestsOfUserService(Integer id) {
        daoRequest.deleteAllRequestsOfUser(id);
    }

    @Override
    public Request deleteRequestService(Request request) {
        return daoRequest.deleteRequest(request);
    }
}
