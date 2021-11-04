package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.Request;
import com.Revature.Project2.repos.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManageRequest {
    private final RequestRepo requestRepo;
    private Request request = new Request();

    @Autowired
    public ManageRequest(RequestRepo requestRepo) {
        this.requestRepo = requestRepo;
    }

    public Request requestAddition(Request request){
        //TODO: Maybe add a validation step here
        request.setStatus("Pending");
        requestRepo.save(request);
        return requestRepo.getById(request.getId());
    }

    public void getAddRequests(){
        //TODO: Write this method
    }

    public void approveAddRequest(){
        //TODO: Write this method. May just remove this method and call the saveMovie method in ManageMovies.
    }
}
