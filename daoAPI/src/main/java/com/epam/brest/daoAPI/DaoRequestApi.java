package com.epam.brest.daoAPI;


import com.epam.brest.Request;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest.model")
public interface DaoRequestApi {


    public List<Request> getAllRequests(Integer id);

    public Request getRequestByIdr (Integer idR);

    public List<Request> saveRequestsForNewUser(Integer id,  List<String> groupe);

    public List<Request> saveRequestsWhenNewGroupe(String groupe, List<Integer> usersId );

    public Request updateRequest(Request request);

    public List<Request> updateAllRequestsForUser(List<Request> requests);

    public Request flushRequestInfo (Request request);

    public void deleteAllRequestsOfUser(Integer id);

    public Request deleteRequest(Request request);
}
