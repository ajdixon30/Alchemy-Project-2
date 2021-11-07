package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Login {
    private final UserRepo userRepo;
    private final Validation validation;


    @Autowired
    public Login(UserRepo userRepo, Validation validation) {
        this.userRepo = userRepo;
        this.validation = validation;
    }

    //Login for users
    public HttpStatus userLogin(String username, String password){
        HttpStatus status;

        User user = userRepo.getById(username);

        //Validate the username and password
        boolean loginTrue = validation.validateUser(username, password, user);

        if(loginTrue){
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.UNAUTHORIZED;
        }
        return status;
    }
}
