package com.Revature.Project2.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoginTest {

    @Autowired
    Login login;

    @Test
    public void testUserLogin(){
        assertEquals(HttpStatus.OK, login.userLogin("user", "pass"));
    }

    @Test
    public void testAdminLogin(){
        assertEquals(HttpStatus.OK, login.adminLogin("use", "pass"));
    }

    @Test
    public void testIfUserCantAdminLogin(){assertEquals(HttpStatus.UNAUTHORIZED,
            login.adminLogin("user", "pass"));}

}
