package com.epam.brest;

//import com.epam.brest.daoAPI.DaoRequestApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@ComponentScan("com.epam.brest")
public class DaoRequestFromLectorImpl implements DaoRequestFromLectorApi {

    private final Logger logger = LogManager.getLogger(DaoRequestFromLectorImpl.class);

    @Autowired
    private RequestFromLectorJpaRepository requestFromLectorRepository;



    public List<RequestFromLector> getAllRequestsFromLectorByIdLector(Integer id) {
        logger.info("GET ALL REQUESTS OF USER {}" + id);
        return (List<RequestFromLector>) requestFromLectorRepository.findAllByForeignKey(id);
    }

    public RequestFromLector getRequestFromLectorByRequestId(Integer idR){
        logger.info("GET REQUEST BY IDR {}" + idR);
        return (RequestFromLector) requestFromLectorRepository.findById(idR).get();
    }

    public List<RequestFromLector> createEmptyRequestsForNewLector(Integer id, List<String> groupe) {
        logger.info("SAVE Requests for new User {} " + id);
        return (List<RequestFromLector>) requestFromLectorRepository.createRequestsforUser(groupe, id);
    }

    public List<RequestFromLector> createRequestsForLectorsWhenCreateNewGroup(String groupe, List<Integer> usersId ){
        logger.info("SAVE Request for new Groupe {} " + groupe);
        return requestFromLectorRepository.addGroupeInRequests(usersId, groupe);
    }

    public RequestFromLector updateRequestFromLector(RequestFromLector request){
        logger.info("Update Request after change {} " + request);
        return (RequestFromLector) requestFromLectorRepository.saveAndFlush(request);
    }


    public List<RequestFromLector> updateAllRequestsForLector(List<RequestFromLector> requests){
        logger.info("Update All Requests for User after change {} ");
        return (List<RequestFromLector>) requestFromLectorRepository.saveAllAndFlush((Iterable<RequestFromLector>) requests);
    }

    public RequestFromLector flushRequestForLector(RequestFromLector request){
        logger.info("Flush Request to null position {} " + request);
        request.setNumberOfPairs("0");
        request.setSubjectOfLector("0000");
        request.setDate(new Date());
        return requestFromLectorRepository.saveAndFlush(request);
    }

    public void deleteAllRequestsFromLector(Integer id){
        logger.info("Delete Requests when User deleted {User.id} =  " + id);
        List <RequestFromLector> requests = requestFromLectorRepository.findAllByForeignKey(id);
        requestFromLectorRepository.deleteAll((Iterable<? extends RequestFromLector>) requests);
    }

    @Override
    public RequestFromLector deleteRequestFromLector(RequestFromLector request) {
        return requestFromLectorRepository.deleteRequest(request);
    }
}
