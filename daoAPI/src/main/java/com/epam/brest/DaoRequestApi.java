package com.epam.brest;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EntityScan("com.epam.brest")
public interface DaoRequestApi {


    public List<RequestFromLector> getAllRequests(Integer id);

    public RequestFromLector getRequestByIdr (Integer idR);

    public List<RequestFromLector> saveRequestsForNewUser(Integer id, List<String> groupe);

    public List<RequestFromLector> saveRequestsWhenNewGroupe(String groupe, List<Integer> usersId );

    public RequestFromLector updateRequest(RequestFromLector request);

    public List<RequestFromLector> updateAllRequestsForUser(List<RequestFromLector> requests);

    public RequestFromLector flushRequestInfo (RequestFromLector request);

    public void deleteAllRequestsOfUser(Integer id);

    public RequestFromLector deleteRequest(RequestFromLector request);
}
