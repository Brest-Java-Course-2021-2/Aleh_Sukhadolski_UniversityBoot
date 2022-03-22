package com.epam.brest;

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
        logger.info("Get all requests from lector service " + idLector);
        return (List<RequestFromLector>) daoRequestFromLector.getAllRequestsFromLectorByIdLector(idLector);
    }

    @Override
    public RequestFromLector getRequestOfLectorByIdRequestService(Integer idRequest) {
        logger.info("Get request from lector service " + idRequest);
        return (RequestFromLector) daoRequestFromLector.getRequestFromLectorByRequestId(idRequest);
    }

    @Override
    public List<RequestFromLector> saveEmptyRequestsForNewLectorService(Integer idLector) {
        logger.info("Create empty requests for the new lector service " + idLector);
        List<String> groups = daoGroup.getAllGroupsNames();
        return (List<RequestFromLector>) daoRequestFromLector.createEmptyRequestsForNewLector(idLector, groups);
    }

    @Override
    public List<RequestFromLector> saveRequestsForLectorsWhenCreateNewGroupeService(String group) {
        logger.info("Create empty requests for all lectors when created new group service " + group);
        List<Lector> lectors = daoLector.getAllLectors();
        List<Integer> idLectors = new ArrayList<>();
        for (Lector lector : lectors) { idLectors.add(lector.getIdLector()); }
        daoGroup.insertNewGroup(group);
        return (List<RequestFromLector>) daoRequestFromLector.createRequestsForLectorsWhenCreateNewGroup(group, idLectors);
    }

    @Override
    public RequestFromLector updateRequestFromLectorService(RequestFromLector requestFromLector) {
        logger.info("Update request from lector service " + requestFromLector);
        return (RequestFromLector) daoRequestFromLector.updateRequestFromLector(requestFromLector);
    }

    @Override
    public List<RequestFromLector> updateAllRequestsForLectorsService(List<RequestFromLector> requestsFromLector) {
        logger.info("Update all requests from the lector service " + requestsFromLector);
        return (List<RequestFromLector>) daoRequestFromLector.updateAllRequestsForLector(requestsFromLector);
    }

    @Override
    public RequestFromLector flushRequestFromLectorService(RequestFromLector requestFromLector) {
        logger.info("Flush request to the empty request for the lector service " + requestFromLector);
        return (RequestFromLector) daoRequestFromLector.flushRequestForLector(requestFromLector);
    }

    @Override
    public void deleteAllRequestsFromLectorService(Integer idLector) {
        logger.info("Delete all requests for the lector service " + idLector);
        daoRequestFromLector.deleteAllRequestsFromLector(idLector);
    }

    @Override
    public RequestFromLector deleteRequestFromLectorService(RequestFromLector requestFromLector) {
        logger.info("Delete request for the lector service " + requestFromLector);
        return daoRequestFromLector.deleteRequestFromLector(requestFromLector);
    }

}
