package com.Revature.Project2.beans.controllers;

import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.services.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final Login login;

    @Autowired
    public UserController(Login login) {
        this.login = login;
    }

    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus get(@RequestParam String username, String password){
        //May need to send the user data to the front end in a response body
        return login.userLogin(username, password);
    }

//    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.CREATED)
//    public User post(@RequestBody User user){
//        login.save(user);
//        return login.get(user.getUsername());
//    }
}
