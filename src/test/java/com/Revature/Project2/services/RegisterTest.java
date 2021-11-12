package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@SpringBootTest
public class RegisterTest {

    @Autowired
    private Register register;

    @Test
    public void testIfUserRegistered(){
        User u = new User("user", "pass", "James",
                "James", false, new ArrayList<>());

//        when(v.validateName("James", "James")).thenReturn(true);
//        when(v.validateUserCreds("user", "pass")).thenReturn(true);

        Assert.assertEquals(HttpStatus.CREATED, register.userRegister(u));
    }


}
