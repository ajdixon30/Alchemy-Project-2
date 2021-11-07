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

    @GetMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public Request get(@RequestParam Integer id){
        return manageRequest.getAddRequest(id);
    }

    @PostMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus post(@RequestBody Request request){
        return manageRequest.requestAddition(request);
    }

    @PutMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus put(@RequestBody Request request){
        return manageRequest.changeRequestStatus(request);//Needs the request id and the requestStatus
    }

    @DeleteMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus delete(@RequestBody Request request){
        return manageRequest.removeRequest(request);
    }
}
