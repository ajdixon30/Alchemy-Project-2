package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class Register {
    private final UserRepo userRepo;
    private final Validation validation;

    @Autowired
    public Register(UserRepo userRepo, Validation validation) {
        this.userRepo = userRepo;
        this.validation = validation;
    }

    //Registration for users
    public HttpStatus userRegister(User user){
        HttpStatus status;

        if(validation.userExists(user.getUsername())){
           status = HttpStatus.NOT_ACCEPTABLE;
            return status;
        }

        if(validation.validateName(user.getFirstName(), user.getLastName()) &&
                validation.validateUserCreds(user.getUsername(), user.getPassword())){
            userRepo.save(user);
            status = HttpStatus.ACCEPTED;
            System.out.println("User Registered");
        } else {
            status = HttpStatus.NOT_ACCEPTABLE;
        }
        return status;
    }

}
