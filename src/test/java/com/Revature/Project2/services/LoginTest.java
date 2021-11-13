package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.repos.UserRepo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the Login service. This class uses Spring Boot Test and JUnit 5
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {

    private final Login login;
    private final UserRepo userRepo;


    @Autowired
    public LoginTest(Login login, UserRepo userRepo){
        this.login = login;
        this.userRepo = userRepo;
    }

    /**
     * adds 2 users (admin and regular) to test tables
     */
    @BeforeAll
    public void open(){
        userRepo.save(new User("user", "pass",
                "J", "B", false, new ArrayList<>()));
        userRepo.save(new User("use", "pass",
                "J", "B", true, new ArrayList<>()));
    }

    @AfterAll
    public void destroy(){userRepo.deleteAll();}

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
