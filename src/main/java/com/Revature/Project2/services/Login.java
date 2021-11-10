package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class that handles logins for users and admin
 */
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

    /**
     * Helper class for both logins
     * @param username username from request parameter
     * @param password password from request parameter
     * @return true/false if user exists or not
     */
    public boolean login(String username, String password){
        return validation.validateUser(username, password);
    }

    /**
     * Login for CineFile users
     * @param username username from request parameter
     * @param password password from request parameter
     * @return HttpStatus if login is valid or not
     */
    public HttpStatus userLogin(String username, String password) {

        if(username != null || password != null) {

            //checks for user entry in db
            if (!validation.userExists(username)) {
                return HttpStatus.NOT_FOUND;
            }

            //Validate the username and password
            User u = userRepo.getById(username);
            if (u.isAdmin()) {
                return HttpStatus.NOT_ACCEPTABLE;
            }

            if (login(username, password)) {
                return HttpStatus.OK;
            } else {
                return HttpStatus.UNAUTHORIZED;
            }
        }else {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    /**
     * Login for admin
     * @param username admin username from request parameter
     * @param password admin password from request parameter
     * @return HttpStatus if admin login is valid or not
     */
    public HttpStatus adminLogin(String username, String password){
        if(username != null || password != null){

            //checks for user entry in db
            if (!validation.userExists(username)) {
                return HttpStatus.NOT_FOUND;
            }

            if (login(username, password)){

                User user = userRepo.getById(username);

                if (user.isAdmin()) {
                    return HttpStatus.OK;
                } else {
                    return HttpStatus.UNAUTHORIZED;
                }

            } else {
                return HttpStatus.UNAUTHORIZED;
            }
        } else {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }
}
