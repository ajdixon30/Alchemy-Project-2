package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration
public class RegisterTest {

    @Mock
    private Register register;

    @Test
    public void testIfUserRegistered(){
        User u = new User("user", "pass", "James",
                "James", false, new ArrayList<>());
        when(register.userRegister(u));
        Assert.assertEquals(HttpStatus.CREATED, register.userRegister(u));
    }


}
