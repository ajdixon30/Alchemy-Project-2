package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class Login {
    private final UserRepo userRepo;


    @Autowired
    public Login(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //Login for users
    public HttpStatus userLogin(String username, String password){
        Validation validation = new Validation();
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
