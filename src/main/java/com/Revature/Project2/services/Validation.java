package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.Rating;
import com.Revature.Project2.beans.pojos.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//TODO: Decide if we want to make this class a bean or keep it as a Util class
public class Validation implements Validator {

    public Validation(){
    }

    @Override
    public boolean supports(Class clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"username", "username is empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "password is empty");
    }

    public boolean validateUser(String username, String password, User user){
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }

    public void validateName(String name){
        //TODO: Write this method
    }

}
