package com.epam.brest.service;

import com.epam.brest.daoAPI.DaoRequestApi;
import com.epam.brest.model.Request;
import com.epam.brest.serviceapi.RequestServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Component
@ComponentScan("com.epam.brest.*")
@EntityScan("com.epam.brest.model")
@Service
public class RequestServiceImpl implements RequestServiceApi {
    @Autowired
    private DaoRequestApi daoRequest;

    @Override
    public List<Request> getAllRequestsService(Integer id) {
        return null;
    }

    @Override
    public Request getRequestByIdrService(Integer idR) {
        return null;
    }

    @Override
    public List<Request> saveRequestsForNewUserService(Integer id, List<String> groupe) {
        return null;
    }

    @Override
    public List<Request> saveRequestsWhenNewGroupeService(String groupe, List<Integer> usersId) {
        return null;
    }

    @Override
    public Request updateRequestService(Request request) {
        return null;
    }

    @Override
    public List<Request> updateAllRequestsForUserService(List<Request> requests) {
        return null;
    }

    @Override
    public Request flushRequestInfoService(Request request) {
        return null;
    }

    @Override
    public void deleteAllRequestsOfUserService(Integer id) {

    }
}
