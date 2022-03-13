package com.epam.brest.serviceapi;


import com.epam.brest.RequestFromLector;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest.model")
public interface RequestServiceApi {

    List<RequestFromLector> getAllRequestsService(Integer id);

    RequestFromLector getRequestByIdrService (Integer idR);

    List<RequestFromLector> saveRequestsForNewUserService(Integer id);

    List<RequestFromLector> saveRequestsWhenNewGroupeService(String groupe);

    RequestFromLector updateRequestService(RequestFromLector request);

    List<RequestFromLector> updateAllRequestsForUserService(List<RequestFromLector> requests);

    RequestFromLector flushRequestInfoService (RequestFromLector request);

    void deleteAllRequestsOfUserService (Integer id);

    public RequestFromLector deleteRequestService(RequestFromLector request);
}
