package com.epam.brest;

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
@ComponentScan("com.epam.brest.*")
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
    public List<RequestFromLector> getAllRequestsService(Integer id) {
        return (List<RequestFromLector>) daoRequest.getAllRequests(id);
    }

    @Override
    public RequestFromLector getRequestByIdrService(Integer idR) {
        return (RequestFromLector) daoRequest.getRequestByIdr(idR);
    }

    @Override
    public List<RequestFromLector> saveRequestsForNewUserService(Integer id) {
        List<String> groupes = daoGroupe.getAllGroupeNames();
        return (List<RequestFromLector>) daoRequest.saveRequestsForNewUser(id, groupes);
    }

    @Override
    public List<RequestFromLector> saveRequestsWhenNewGroupeService(String groupe) {
        List<Lector> users = daoUser.getAllUsers();
        List<Integer> ids = new ArrayList<>();
        for (Lector u : users) { ids.add(u.getIdLector()); }
        daoGroupe.insertNewGroupe(groupe);
        return (List<RequestFromLector>) daoRequest.saveRequestsWhenNewGroupe(groupe, ids);
    }

    @Override
    public RequestFromLector updateRequestService(RequestFromLector request) {
        return (RequestFromLector) daoRequest.updateRequest(request);
    }

    @Override
    public List<RequestFromLector> updateAllRequestsForUserService(List<RequestFromLector> requests) {
        return (List<RequestFromLector>) daoRequest.updateAllRequestsForUser(requests);
    }

    @Override
    public RequestFromLector flushRequestInfoService(RequestFromLector request) {
        return (RequestFromLector) daoRequest.flushRequestInfo(request);
    }

    @Override
    public void deleteAllRequestsOfUserService(Integer id) {
        daoRequest.deleteAllRequestsOfUser(id);
    }

    @Override
    public RequestFromLector deleteRequestService(RequestFromLector request) {
        return daoRequest.deleteRequest(request);
    }
}
