package com.epam.brest.serviceapi;


import com.epam.brest.RequestFromLector;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

public interface RequestFromLectorServiceApi {

    List<RequestFromLector> getAllRequestsFromLectorService(Integer id);

    RequestFromLector getRequestOfLectorByIdRequestService(Integer idR);

    List<RequestFromLector> saveEmptyRequestsForNewLectorService(Integer id);

    List<RequestFromLector> saveRequestsForLectorsWhenCreateNewGroupeService(String groupe);

    RequestFromLector updateRequestFromLectorService(RequestFromLector request);

    List<RequestFromLector> updateAllRequestsForLectorsService(List<RequestFromLector> requests);

    RequestFromLector flushRequestFromLectorService(RequestFromLector request);

    void deleteAllRequestsFromLectorService(Integer id);

    public RequestFromLector deleteRequestFromLectorService(RequestFromLector request);

}
