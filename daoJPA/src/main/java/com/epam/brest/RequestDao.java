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
public class RequestDao implements DaoRequestApi {

    private final Logger logger = LogManager.getLogger(RequestDao.class);

    @Autowired
    private RequestJpaRepository requestRepository;



    public List<RequestFromLector> getAllRequests(Integer id) {
        logger.info("GET ALL REQUESTS OF USER {}" + id);
        return (List<RequestFromLector>) requestRepository.findAllByForeignKey(id);
    }

    public RequestFromLector getRequestByIdr (Integer idR){
        logger.info("GET REQUEST BY IDR {}" + idR);
        return (RequestFromLector) requestRepository.findById(idR).get();
    }

    public List<RequestFromLector> saveRequestsForNewUser(Integer id, List<String> groupe) {
        logger.info("SAVE Requests for new User {} " + id);
        return (List<RequestFromLector>) requestRepository.createRequestsforUser(groupe, id);
    }

    public List<RequestFromLector> saveRequestsWhenNewGroupe(String groupe, List<Integer> usersId ){
        logger.info("SAVE Request for new Groupe {} " + groupe);
        return requestRepository.addGroupeInRequests(usersId, groupe);
    }

    public RequestFromLector updateRequest(RequestFromLector request){
        logger.info("Update Request after change {} " + request);
        return (RequestFromLector) requestRepository.saveAndFlush(request);
    }


    public List<RequestFromLector> updateAllRequestsForUser(List<RequestFromLector> requests){
        logger.info("Update All Requests for User after change {} ");
        return (List<RequestFromLector>) requestRepository.saveAllAndFlush((Iterable<RequestFromLector>) requests);
    }

    public RequestFromLector flushRequestInfo (RequestFromLector request){
        logger.info("Flush Request to null position {} " + request);
        request.setNumberOfPairs("0");
        request.setSubjectOfLector("0000");
        request.setDate(new Date());
        return requestRepository.saveAndFlush(request);
    }

    public void deleteAllRequestsOfUser(Integer id){
        logger.info("Delete Requests when User deleted {User.id} =  " + id);
        List <RequestFromLector> requests = requestRepository.findAllByForeignKey(id);
        requestRepository.deleteAll((Iterable<? extends RequestFromLector>) requests);
    }

    @Override
    public RequestFromLector deleteRequest(RequestFromLector request) {
        return requestRepository.deleteRequest(request);
    }
}
