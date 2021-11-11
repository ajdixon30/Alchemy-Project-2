package com.Revature.Project2.beans.controllers;

import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.services.Login;
import com.Revature.Project2.services.Register;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for user logic
 */
@RestController
public class UserController {
    private final Login login;
    private final Register register;

    @Autowired
    public UserController(Login login, Register register) {
        this.login = login;
        this.register = register;
    }

    /**
     * Login for user
     * @param username
     * @param password
     * @return
     */
    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@RequestParam String username, String password){
        //May need to send the user data to the front end in a response body
        return new ResponseEntity(login.userLogin(username, password));
    }

    /**
     * Login for admin
     * @param username
     * @param password
     * @return
     */
    @GetMapping(value="/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAdmin(@RequestParam String username, String password){
        return new ResponseEntity(login.adminLogin(username, password));
    }

    /**
     * Posts a new user
     * @param user
     * @return
     */
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity post(@RequestBody User user){
        return new ResponseEntity(register.userRegister(user));
    }

}
