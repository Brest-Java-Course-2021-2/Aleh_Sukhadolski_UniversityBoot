package com.epam.brest;


import com.epam.brest.RequestFromLector;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.List;

public interface RequestFromLectorServiceApi {

    List<RequestFromLector> getAllRequestsFromLectorService(Integer id);

    RequestFromLector getRequestOfLectorByIdRequestService(Integer idR);

    RequestFromLector updateRequestFromLectorService(RequestFromLector request);

    RequestFromLector flushRequestFromLectorService(RequestFromLector request);

}
