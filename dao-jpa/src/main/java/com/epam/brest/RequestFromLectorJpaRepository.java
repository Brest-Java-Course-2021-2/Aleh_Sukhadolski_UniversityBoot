package com.epam.brest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface RequestFromLectorJpaRepository extends JpaRepository<RequestFromLector, Integer> {

    default List<RequestFromLector> findAllByForeignKey(Integer id) {
        return (List<RequestFromLector>) findAll().stream()
                                        .filter(req -> id == req.getIdLector())
                                        .collect(Collectors.toList());
    }

    default RequestFromLector updateRequest(RequestFromLector requestFromLector){
       return saveAndFlush(requestFromLector);
    }

    default List<RequestFromLector> createRequestsforNewUser(List<String> groups, Integer id){
        List<RequestFromLector> requestsFromLector = groups.stream()
            .flatMap(group -> Stream.of(new RequestFromLector(id, group, " ", "    ", new Date())))
            .collect(Collectors.toList());
        return saveAllAndFlush(requestsFromLector);
    }

    default  List<RequestFromLector> addNewGroupeInAllLectorRequests(List <Integer> idLectors, String group){
        List<RequestFromLector> requestsFromLector = idLectors.stream()
            .flatMap(idLector -> Stream.of(new RequestFromLector(idLector, group, " ", "    ", new Date())))
            .collect(Collectors.toList());
       return saveAllAndFlush((List<RequestFromLector>)requestsFromLector);
    }

    default RequestFromLector deleteRequest(RequestFromLector requestFromLector)
    {
        deleteById(requestFromLector.getIdRequest());
        return requestFromLector;
    }

}
