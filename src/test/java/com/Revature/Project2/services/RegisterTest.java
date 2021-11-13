package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.repos.UserRepo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegisterTest {

    private final UserRepo userRepo;
    private final Register register;

    @Autowired
    public RegisterTest(Register register, UserRepo userRepo){
        this.register = register;
        this.userRepo = userRepo;
    }

    @AfterAll
    public void destroy(){
        userRepo.deleteAll();
    }

    @Test
    public void testIfUserAndAdminRegistered(){
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
