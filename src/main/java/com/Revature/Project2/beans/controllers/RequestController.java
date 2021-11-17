package com.Revature.Project2.beans.controllers;

import com.Revature.Project2.beans.pojos.Request;
import com.Revature.Project2.services.ManageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling Request business logic
 */
@RestController
public class RequestController {
    private final ManageRequest manageRequest;

    @Autowired
    public RequestController(ManageRequest manageRequest) {
        this.manageRequest = manageRequest;
    }

    /**
     * Gets a request from DB using id
     * @param id unique identifier for a Request
     * @return the specified request if it exists
     */
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @GetMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public Request get(@RequestParam Integer id){
        return manageRequest.getAddRequest(id);
    }

    /**
     * Gets all requests from the DB
     * @return Returns a list of the Request objects
     */
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @GetMapping(value = "/request/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Request> get(){
        return manageRequest.getAllRequests();
    }

    /**
     * Saves a new request to the DB
     * @param request new request object provided in the request body
     * @return a ResponseEntity initialized by requestAddition(request)
     */
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PostMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> post(@RequestBody Request request){
        return new ResponseEntity(manageRequest.requestAddition(request.getAddRequest()));
    }

    /**
     * Updates a request in DB
     * @param request updated Request provided by request body
     * @return ResponseEntity initialized by updateRequest(request)
     */
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PutMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity put(@RequestBody Request request){
        return new ResponseEntity(manageRequest.updateRequest(request));
    }

    /**
     * Removes request from DB
     * @param request Request to be removed. provided from request body
     * @return
     */
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @DeleteMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus delete(@RequestBody Request request){
        return manageRequest.removeRequest(request);
    }
}
