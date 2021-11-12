package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.repos.UserRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RegisterTest {

    @InjectMocks
    private Register register;

    @Mock
    private Validation v;

    @Mock
    private UserRepo userRepo;

    @Test
    public void testIfUserRegistered(){
        User u = new User("user", "pass", "James",
                "James", false, new ArrayList<>());

        when(v.validateName("James", "James")).thenReturn(true);
        when(v.validateUserCreds("user", "pass")).thenReturn(true);

        Assert.assertEquals(HttpStatus.CREATED, register.userRegister(u));
    }


}
