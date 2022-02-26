package com.epam.brest.serviceapi;


import com.epam.brest.model.Request;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest.model")
public interface RequestServiceApi {

    List<Request> getAllRequestsService(Integer id);

    Request getRequestByIdrService (Integer idR);

    List<Request> saveRequestsForNewUserService(Integer id,  List<String> groupe);

    List<Request> saveRequestsWhenNewGroupeService(String groupe, List<Integer> usersId );

    Request updateRequestService(Request request);

    List<Request> updateAllRequestsForUserService(List<Request> requests);

    Request flushRequestInfoService (Request request);

    void deleteAllRequestsOfUserService (Integer id);
}
