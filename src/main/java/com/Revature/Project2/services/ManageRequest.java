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
    private final Validation validation;
    private HttpStatus status;

    @Autowired
    public ManageRequest(RequestRepo requestRepo, Validation validation) {
        this.requestRepo = requestRepo;
        this.validation = validation;
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
        if(validation.requestExists(id)) {
            return requestRepo.getById(id);
        }
        return null;
    }

    public HttpStatus changeRequestStatus(Request request){
        if(request.getRequestStatus() == null){
            request.setRequestStatus("Pending");
        }

        boolean notEmpty = validation.validString(request.getRequestStatus());
        if(!notEmpty){
            status = HttpStatus.NOT_ACCEPTABLE;
            return status;
        }

        Integer id = request.getId();
        if(validation.requestExists(id)){

            //Gets the addRequest from the database and saves it to the Request object since it won't be in request
            //object from the front end
            if(request.getAddRequest() == null){
                Request tempRequest = requestRepo.getById(id);
                request.setAddRequest(tempRequest.getAddRequest());
            }

            notEmpty = validation.validString(request.getAddRequest());
            if(!notEmpty) {
                status = HttpStatus.NOT_ACCEPTABLE;
                return status;
            }
            requestRepo.save(request);
            return HttpStatus.ACCEPTED;
        } else{
            return HttpStatus.NOT_FOUND;
        }
    }

    public HttpStatus removeRequest(Request request){
        Integer id = request.getId();
        if(validation.requestExists(id)){
            requestRepo.deleteById(id);
            return HttpStatus.ACCEPTED;
        } else{
            return HttpStatus.NOT_FOUND;
        }
    }
}
