package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RegisterTest {

    private Register register;

    @Autowired
    public RegisterTest(Register register){this.register = register;}

    @Test
    public void testIfUserRegistered(){
        User u = new User("user", "pass", "James",
                "James", false, new ArrayList<>());

        assertEquals(HttpStatus.CREATED, register.userRegister(u));

        User user = new User("use", "pass", "James",
                "James", true, new ArrayList<>());

        assertEquals(HttpStatus.CREATED, register.userRegister(user));
    }

    @Test
    public void testForDuplicateAccountCreation(){
        User u = new User("user", "pass", "James",
                "James", false, new ArrayList<>());
        assertEquals(HttpStatus.FORBIDDEN, register.userRegister(u));
    }
}
