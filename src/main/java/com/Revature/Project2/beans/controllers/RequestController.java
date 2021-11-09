package com.Revature.Project2.beans.controllers;

import com.Revature.Project2.beans.pojos.Request;
import com.Revature.Project2.services.ManageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestController {
    private final ManageRequest manageRequest;

    @Autowired
    public RequestController(ManageRequest manageRequest) {
        this.manageRequest = manageRequest;
    }

    //Gets the request from the database
    @GetMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public Request get(@RequestParam Integer id){
        return manageRequest.getAddRequest(id);
    }

    //Saves a new request to the database
    @PostMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus post(@RequestBody Request request){
        return manageRequest.requestAddition(request);
    }

    //Updates a request in the database
    @PutMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus put(@RequestBody Request request){
        return manageRequest.changeRequestStatus(request);//Needs the request id and the requestStatus
    }

    //Deletes a request in the database
    @DeleteMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus delete(@RequestBody Request request){
        return manageRequest.removeRequest(request);
    }
}
