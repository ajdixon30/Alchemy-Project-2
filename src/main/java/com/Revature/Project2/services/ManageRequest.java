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
        //TODO: Write this method

        if(requestRepo.existsById(id)) {
            return requestRepo.getById(id);
        }
        return null;
    }

    public void approveAddRequest(){
        //TODO: Write this method.
    }

    public void removeRequest(){
        //TODO: Write this method.
    }
}
