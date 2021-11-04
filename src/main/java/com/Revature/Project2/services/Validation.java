package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.User;

//TODO: Decide if we want to make this class a bean or keep it as a Util class
public class Validation {

    public Validation() {
    }

    public boolean validateUser(String username, String password, User user){
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }

    public void validateName(String name){
        //TODO: Write this method. For validating first and last names.


    }

    public void validateNumber(String number){
        //TODO: Write this method. For validating that the rating is a number between 1 and 5.
    }
}
