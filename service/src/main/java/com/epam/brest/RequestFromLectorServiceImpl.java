package com.epam.brest;

import com.epam.brest.serviceapi.RequestFromLectorServiceApi;
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
public class RequestFromLectorServiceImpl implements RequestFromLectorServiceApi {

    private final Logger logger = LogManager.getLogger(RequestFromLectorServiceImpl.class);

    @Autowired
    private DaoRequestFromLectorApi daoRequestFromLector;

    @Autowired
    private DaoGroupApi daoGroup;

    @Autowired
    private DaoLectorApi daoLector;

    @Override
    public List<RequestFromLector> getAllRequestsFromLectorService(Integer id) {
        return (List<RequestFromLector>) daoRequestFromLector.getAllRequestsFromLectorByIdLector(id);
    }

    @Override
    public RequestFromLector getRequestOfLectorByIdRequestService(Integer idR) {
        return (RequestFromLector) daoRequestFromLector.getRequestFromLectorByRequestId(idR);
    }

    @Override
    public List<RequestFromLector> saveEmptyRequestsForNewLectorService(Integer id) {
        List<String> groupes = daoGroup.getAllGroupsNames();
        return (List<RequestFromLector>) daoRequestFromLector.createEmptyRequestsForNewLector(id, groupes);
    }

    @Override
    public List<RequestFromLector> saveRequestsForLectorsWhenCreateNewGroupeService(String groupe) {
        List<Lector> users = daoLector.getAllLectors();
        List<Integer> ids = new ArrayList<>();
        for (Lector u : users) { ids.add(u.getIdLector()); }
        daoGroup.insertNewGroup(groupe);
        return (List<RequestFromLector>) daoRequestFromLector.createRequestsForLectorsWhenCreateNewGroup(groupe, ids);
    }

    @Override
    public RequestFromLector updateRequestFromLectorService(RequestFromLector request) {
        return (RequestFromLector) daoRequestFromLector.updateRequestFromLector(request);
    }

    @Override
    public List<RequestFromLector> updateAllRequestsForLectorsService(List<RequestFromLector> requests) {
        return (List<RequestFromLector>) daoRequestFromLector.updateAllRequestsForLector(requests);
    }

    @Override
    public RequestFromLector flushRequestFromLectorService(RequestFromLector request) {
        return (RequestFromLector) daoRequestFromLector.flushRequestForLector(request);
    }

    @Override
    public void deleteAllRequestsFromLectorService(Integer id) {
        daoRequestFromLector.deleteAllRequestsFromLector(id);
    }

    @Override
    public RequestFromLector deleteRequestFromLectorService(RequestFromLector request) {
        return daoRequestFromLector.deleteRequestFromLector(request);
    }
}
