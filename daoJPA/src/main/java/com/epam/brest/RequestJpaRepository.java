package com.epam.brest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public interface RequestJpaRepository extends JpaRepository<RequestFromLector, Integer> {

    default List<RequestFromLector> findAllByForeignKey(Integer id) {
        return (List<RequestFromLector>) findAll().stream()
                                        .filter(req -> id == req.getId())
                                        .collect(Collectors.toList());
    }

    default RequestFromLector updateRequest(RequestFromLector request){
       return saveAndFlush(request);
    }

    default List<RequestFromLector> createRequestsforUser(List<String> groupes, Integer id){
        List<RequestFromLector> requests = groupes.stream()
            .flatMap(groupe -> Stream.of(new RequestFromLector(id, groupe, "0", "0000", new Date())))
            .collect(Collectors.toList());
        return saveAllAndFlush(requests);
    }

    default  List<RequestFromLector> addGroupeInRequests(List <Integer> idUsers, String groupe){
        List<RequestFromLector> requests = idUsers.stream()
            .flatMap(id -> Stream.of(new RequestFromLector(id, groupe, "0", "0000", new Date())))
            .collect(Collectors.toList());
       return saveAllAndFlush((List<RequestFromLector>)requests);
    }

    default RequestFromLector deleteRequest(RequestFromLector request)
    {
        deleteById(request.getIdRequest());
        return request;
    }

}
