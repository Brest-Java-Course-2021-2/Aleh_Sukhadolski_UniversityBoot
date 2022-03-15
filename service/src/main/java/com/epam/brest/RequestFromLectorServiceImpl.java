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
@ComponentScan("com.epam.brest")
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
    public List<RequestFromLector> getAllRequestsFromLectorService(Integer idLector) {
        return (List<RequestFromLector>) daoRequestFromLector.getAllRequestsFromLectorByIdLector(idLector);
    }

    @Override
    public RequestFromLector getRequestOfLectorByIdRequestService(Integer idRequest) {
        return (RequestFromLector) daoRequestFromLector.getRequestFromLectorByRequestId(idRequest);
    }

    @Override
    public List<RequestFromLector> saveEmptyRequestsForNewLectorService(Integer idLector) {
        List<String> groups = daoGroup.getAllGroupsNames();
        return (List<RequestFromLector>) daoRequestFromLector.createEmptyRequestsForNewLector(idLector, groups);
    }

    @Override
    public List<RequestFromLector> saveRequestsForLectorsWhenCreateNewGroupeService(String group) {
        List<Lector> lectors = daoLector.getAllLectors();
        List<Integer> idLectors = new ArrayList<>();
        for (Lector lector : lectors) { idLectors.add(lector.getIdLector()); }
        daoGroup.insertNewGroup(group);
        return (List<RequestFromLector>) daoRequestFromLector.createRequestsForLectorsWhenCreateNewGroup(group, idLectors);
    }

    @Override
    public RequestFromLector updateRequestFromLectorService(RequestFromLector requestFromLector) {
        return (RequestFromLector) daoRequestFromLector.updateRequestFromLector(requestFromLector);
    }

    @Override
    public List<RequestFromLector> updateAllRequestsForLectorsService(List<RequestFromLector> requestsFromLector) {
        return (List<RequestFromLector>) daoRequestFromLector.updateAllRequestsForLector(requestsFromLector);
    }

    @Override
    public RequestFromLector flushRequestFromLectorService(RequestFromLector requestFromLector) {
        return (RequestFromLector) daoRequestFromLector.flushRequestForLector(requestFromLector);
    }

    @Override
    public void deleteAllRequestsFromLectorService(Integer idLector) {
        daoRequestFromLector.deleteAllRequestsFromLector(idLector);
    }

    @Override
    public RequestFromLector deleteRequestFromLectorService(RequestFromLector requestFromLector) {
        return daoRequestFromLector.deleteRequestFromLector(requestFromLector);
    }
}
