package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.repos.UserRepo;


//TODO: Decide if we want to make this class a bean or keep it as a Util class
public class Validation {

    public Validation() {
    }

    //Validates that the inputted username and password match a username and associated password in the database.
    public boolean validateUser(String username, String password, User user){
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }

    //Validates that the inputted first and last name are proper Names.
    public boolean validateName(String firstName, String lastName){
        boolean isRegularName = false;

        if(firstName.matches("[a-zA-Z]{2,25}$")&& lastName.matches("[a-zA-Z]{2,25}$")){
            isRegularName = true;
        } else {
            //TODO: write to FileLogger and return to registration page ("/register")
        }
        return isRegularName;
    }

    //Validates that the username and password meet the requirements for a valid username or password
    public boolean validateUserCreds(String username, String password){
        boolean isRegularCreds = false;

        if(password.matches("[a-zA-Z0-9]{2,25}$")&& username.matches("[a-zA-Z0-9]{2,25}$")){
            isRegularCreds = true;
        } else {
            //TODO: write to FileLogger and return to registration page ("/register")
        }
        return isRegularCreds;
    }

    //Validates that the user exists in the database
    public boolean userExists(UserRepo userRepo, String username){
        return userRepo.existsById(username);
    }

    //Validates that the given string is not empty and fits within the set size range (1-30 characters long)
    public boolean validString(String string){
        return string.matches("[a-zA-Z ]{1,30}$");
    }

}
