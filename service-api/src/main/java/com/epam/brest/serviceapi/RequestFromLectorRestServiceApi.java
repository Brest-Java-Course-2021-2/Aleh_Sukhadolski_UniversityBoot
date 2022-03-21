package com.epam.brest.serviceapi;

import com.epam.brest.RequestFromLector;

import java.util.List;

public interface RequestFromLectorRestServiceApi {

    List<RequestFromLector> getAllRequestsFromLectorService(Integer id);

    RequestFromLector getRequestOfLectorByIdRequestService(Integer idR);

    RequestFromLector updateRequestFromLectorService(RequestFromLector request);

    RequestFromLector flushRequestFromLectorService(RequestFromLector request);

}
