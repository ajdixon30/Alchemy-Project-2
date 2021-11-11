package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.Movie;
import com.Revature.Project2.beans.pojos.Request;
import com.Revature.Project2.repos.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    //Adds request to the database
    public HttpStatus requestAddition(String addRequest){
        Request request = new Request();
        Movie movie = new Movie();

        //Check if the string is not empty (ie does not equal "" or " ")
        boolean notEmpty = validation.validString(addRequest);
        //If empty then send a 406 HTTP response status code
        if(!notEmpty) {
            status = HttpStatus.NOT_ACCEPTABLE;
            return status;
        }

        movie.setTitle(addRequest);
        if(validation.movieExists(movie)){
            status = HttpStatus.NOT_ACCEPTABLE;
            return status;
        }
        //Set addRequest in the Request object
        request.setAddRequest(addRequest);
        //Set the default request status
        request.setRequestStatus("Pending");
        //Save the request to the database
        requestRepo.save(request);
        status = HttpStatus.CREATED;

        return status;
    }

    //Gets a request using its id
    public Request getAddRequest(Integer id){
        //Checks if the request is in the database
        if(validation.requestExists(id)) {
            return requestRepo.getById(id);
        }
        return null;
    }

    //Gets all requests
    public List<Request> getAllRequests(){
        //Checks to make sure the request table is not empty
        if(validation.requestNotEmpty()){
            return requestRepo.findAll();
        } else {
            return null;
        }
    }

    public HttpStatus updateRequest(Request request){
        boolean notEmpty = validation.validString(request.getRequestStatus());
        if(!notEmpty){
            status = HttpStatus.NOT_ACCEPTABLE;
            return status;
        }

        Integer id = request.getId();
        if(validation.requestExists(id)){
            //If requestStatus is null, set requestStatus equal to requestStatus in the database
            if(request.getRequestStatus() == null){
                request.setRequestStatus(requestRepo.getById(request.getId()).getRequestStatus());
            }
            //If addRequest is null, set addRequest equal to addRequest in the database
            if(request.getAddRequest() == null){
                request.setAddRequest(requestRepo.getById(id).getAddRequest());
            }

            //Validate that the request exists in the database
            notEmpty = validation.validString(request.getAddRequest());
            if(!notEmpty) {
                //If empty then send a 406 HTTP response status code
                status = HttpStatus.NOT_ACCEPTABLE;
                return status;
            }
            //Update the request
            requestRepo.save(request);
            //Send a 202 HTTP response status code
            return HttpStatus.ACCEPTED;
        } else{
            //If request does not exist in the database, send a 404 HTTP response status code
            return HttpStatus.NOT_FOUND;
        }
    }

    public HttpStatus removeRequest(Request request){
        boolean notEmpty = validation.validString(request.getRequestStatus());
        if(!notEmpty){
            status = HttpStatus.NOT_ACCEPTABLE;
            return status;
        }
        Integer id = request.getId();
        //If request exists in the database, then delete the request entry
        if(validation.requestExists(id)){
            //Delete request from database
            requestRepo.deleteById(id);
            //Send a 202 HTTP response status code
            return HttpStatus.ACCEPTED;
        } else{
            //If request does not exist in the database, send a 404 HTTP response status code
            return HttpStatus.NOT_FOUND;
        }
    }
}
