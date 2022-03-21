package com.epam.brest;

import com.epam.brest.serviceapi.RequestFromLectorServiceApi;

import java.util.List;

public class RequestFromLectorRestClientImpl implements RequestFromLectorServiceApi {
    @Override
    public List<RequestFromLector> getAllRequestsFromLectorService(Integer id) {
        return null;
    }

    @Override
    public RequestFromLector getRequestOfLectorByIdRequestService(Integer idR) {
        return null;
    }

    @Override
    public List<RequestFromLector> saveEmptyRequestsForNewLectorService(Integer id) {
        return null;
    }

    @Override
    public List<RequestFromLector> saveRequestsForLectorsWhenCreateNewGroupeService(String groupe) {
        return null;
    }

    @Override
    public RequestFromLector updateRequestFromLectorService(RequestFromLector request) {
        return null;
    }

    @Override
    public List<RequestFromLector> updateAllRequestsForLectorsService(List<RequestFromLector> requests) {
        return null;
    }

    @Override
    public RequestFromLector flushRequestFromLectorService(RequestFromLector request) {
        return null;
    }

    @Override
    public void deleteAllRequestsFromLectorService(Integer id) {

    }

    @Override
    public RequestFromLector deleteRequestFromLectorService(RequestFromLector request) {
        return null;
    }
}
