package com.epam.brest;


import com.epam.brest.RequestFromLector;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

public interface RequestFromLectorServiceApi extends RequestFromLectorRestServiceApi{

    void deleteAllRequestsFromLectorService(Integer id);

    public RequestFromLector deleteRequestFromLectorService(RequestFromLector request);

    List<RequestFromLector> saveEmptyRequestsForNewLectorService(Integer id);

    List<RequestFromLector> saveRequestsForLectorsWhenCreateNewGroupeService(String groupe);

    List<RequestFromLector> updateAllRequestsForLectorsService(List<RequestFromLector> requests);

}
