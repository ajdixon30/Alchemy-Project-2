package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.repos.UserRepo;

import java.io.FileWriter;

//TODO: Decide if we want to make this class a bean or keep it as a Util class
public class Validation {

    public Validation() {
    }

    public boolean validateUser(String username, String password, User user){
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }

    public boolean validateName(String firstName, String lastName){
        boolean isRegularName = false;

        if(firstName.matches("[a-zA-Z]{2,25}$")&& lastName.matches("[a-zA-Z]{2,25}$")){
            isRegularName = true;
        } else {
            //TODO: write to FileLogger and return to registration page ("/register")
        }
        return isRegularName;
    }
    public boolean validateUserCreds(String username, String password){
        boolean isRegularCreds = false;

        if(password.matches("[a-zA-Z0-9]{2,25}$")&& username.matches("[a-zA-Z0-9]{2,25}$")){
            isRegularCreds = true;
        } else {
            //TODO: write to FileLogger and return to registration page ("/register")
        }
        return isRegularCreds;
    }
    public boolean userExists(UserRepo user, String username){
        return user.existsById(username);
    }

}
