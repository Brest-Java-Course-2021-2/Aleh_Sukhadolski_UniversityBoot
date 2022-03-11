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



    public List<Request> getAllRequests(Integer id) {
        logger.info("GET ALL REQUESTS OF USER {}" + id);
        return (List<Request>) requestRepository.findAllByForeignKey(id);
    }

    public Request getRequestByIdr (Integer idR){
        logger.info("GET REQUEST BY IDR {}" + idR);
        return (Request) requestRepository.findById(idR).get();
    }

    public List<Request> saveRequestsForNewUser(Integer id,  List<String> groupe) {
        logger.info("SAVE Requests for new User {} " + id);
        return (List<Request>) requestRepository.createRequestsforUser(groupe, id);
    }

    public List<Request> saveRequestsWhenNewGroupe(String groupe, List<Integer> usersId ){
        logger.info("SAVE Request for new Groupe {} " + groupe);
        return requestRepository.addGroupeInRequests(usersId, groupe);
    }

    public Request updateRequest(Request request){
        logger.info("Update Request after change {} " + request);
        return (Request) requestRepository.saveAndFlush(request);
    }


    public List<Request> updateAllRequestsForUser(List<Request> requests){
        logger.info("Update All Requests for User after change {} ");
        return (List<Request>) requestRepository.saveAllAndFlush((Iterable<Request>) requests);
    }

    public Request flushRequestInfo (Request request){
        logger.info("Flush Request to null position {} " + request);
        request.setPairs("0");
        request.setSubject("0000");
        request.setDate(new Date());
        return requestRepository.saveAndFlush(request);
    }

    public void deleteAllRequestsOfUser(Integer id){
        logger.info("Delete Requests when User deleted {User.id} =  " + id);
        List <Request> requests = requestRepository.findAllByForeignKey(id);
        requestRepository.deleteAll((Iterable<? extends Request>) requests);
    }

    @Override
    public Request deleteRequest(Request request) {
        return requestRepository.deleteRequest(request);
    }
}
