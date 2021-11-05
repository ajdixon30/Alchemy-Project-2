package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.Request;
import com.Revature.Project2.repos.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManageRequest {
    private final RequestRepo requestRepo;
    private Request request = new Request();
    private HttpStatus status;
    private Validation validation = new Validation();

    @Autowired
    public ManageRequest(RequestRepo requestRepo) {
        this.requestRepo = requestRepo;
    }

    public HttpStatus requestAddition(Request request){
        boolean notEmpty = validation.validString(request.getAddRequest());
        if(!notEmpty){
            status = HttpStatus.NOT_ACCEPTABLE;
            return status;
        }
        request.setRequestStatus("Pending");
        requestRepo.save(request);
        status = HttpStatus.CREATED;
        return status;
    }

    public Request getAddRequest(Integer id){
        if(requestRepo.existsById(id)) {
            return requestRepo.getById(id);
        }
        return null;
    }

    public HttpStatus changeRequestStatus(Request request){
        boolean notEmpty = validation.validString(request.getRequestStatus());
        if(!notEmpty){
            status = HttpStatus.NOT_ACCEPTABLE;
            return status;
        }

        Integer id = request.getId();
        if(requestRepo.existsById(id)){
            Request tempRequest = requestRepo.getById(id);
            //Gets the addRequest from the database and saves it to the Request object since it won't be in request
            //object from the front end
            request.setAddRequest(tempRequest.getAddRequest());
            requestRepo.save(request);
            return HttpStatus.ACCEPTED;
        } else{
            return HttpStatus.NOT_FOUND;
        }
    }

    public void removeRequest(){
        //TODO: Write this method.
    }
}
