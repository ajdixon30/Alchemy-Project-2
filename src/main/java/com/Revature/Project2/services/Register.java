package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for registering new users
 */
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

    /**
     * Registers a new user
     * @param user user from RequestBody to be sent when 'Register' is pressed
     * @return an HttpStatus depending on if the user is created or some error occurs
     */
    public HttpStatus userRegister(User user){

        if(validation.userExists(user.getUsername())){
            return HttpStatus.FORBIDDEN;
        }

        if(validation.validateName(user.getFirstName(), user.getLastName()) &&
                validation.validateUserCreds(user.getUsername(), user.getPassword())){
            userRepo.save(user);
            System.out.println("User Registered");
            return HttpStatus.CREATED;
        } else {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

}
