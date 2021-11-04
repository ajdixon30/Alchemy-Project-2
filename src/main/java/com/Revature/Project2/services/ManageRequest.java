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
        request.setStatus("Pending");
        requestRepo.save(request);
        status = HttpStatus.OK;
        return status;
    }

    public void getAddRequests(){
        //TODO: Write this method
    }

    public void approveAddRequest(){
        //TODO: Write this method. May just remove this method and call the saveMovie method in ManageMovies.
    }
}
