package com.epam.brest.dao.model;

import com.epam.brest.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    default List<Request> findAllByForeignKey(Integer id) {
        return (List<Request>) findAll().stream()
                                        .filter(req -> id == req.getId())
                                        .collect(Collectors.toList());
    }

    default Request updateRequest(Request request){
       return saveAndFlush(request);
    }

    default List<Request> createRequestsforUser(List<String> groupes, Integer id){
        List<Request> requests = groupes.stream()
                .flatMap(groupe -> Stream.of(new Request(id, groupe, "0", "0000", new Date())))
                .collect(Collectors.toList());
        return saveAllAndFlush(requests);
    }

    default  List<Request> addGroupeInRequests(List <Integer> idUsers, String groupe){
        List<Request> requests = idUsers.stream()
                    .flatMap(id -> Stream.of(new Request(id, groupe, "0", "0000", new Date())))
                    .collect(Collectors.toList());
       return saveAllAndFlush((List<Request>)requests);
    }

}
