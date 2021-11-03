package com.Revature.Project2.beans.controllers;

import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.services.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserController {
    private final Login login;
    private HttpStatus status;

    @Autowired
    public UserController(Login login) {
        this.login = login;
    }

    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus get(@RequestBody User user){
//        login.userRole(user);
        return login.userLogin(user);
    }
}
