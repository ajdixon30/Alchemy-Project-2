package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.Movie;
import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.repos.MovieRepo;
import com.Revature.Project2.repos.RatingRepo;
import com.Revature.Project2.repos.RequestRepo;
import com.Revature.Project2.repos.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValidationTest {

    @InjectMocks
    private Validation validation;

    @Mock
    private UserRepo userRepo;

    @Mock
    private MovieRepo movieRepo;

    @Mock
    private RequestRepo requestRepo;

    @Mock
    private RatingRepo ratingRepo;

    @Test
    public void testForBadValidation(){
        assertFalse(validation.validateUser("ooga", "booga"));
    }

    @Test
    public void testForGoodValidation(){
        when(validation.userExists("user")).thenReturn(true);
        when(userRepo.getById("user")).thenReturn(new User("user", "pass", "J", "B", false, new ArrayList<>()));

        assertTrue(validation.validateUser("user", "pass"));
    }

    @Test
    public void testForInvalidName(){
        assertFalse(validation.validateName("uzer32r10#", "t23jt8ghn"));
    }

    @Test
    public void testForValidName(){
        assertTrue(validation.validateName("Good", "Strings"));
    }

    @Test
    public void testForValidCredentials(){
        assertTrue(validation.validateUserCreds("user12498Name", "vAlidpass1240word"));
    }

    @Test
    public void testForInvalidCredentials(){
        assertFalse(validation.validateUserCreds("UZAA%@#&%*(--++++","%@#(&@^(@$&^()(^*)@($"));
    }

    @Test
    public void testIfMovieExists(){
        when(movieRepo.movieSearch("MockTitle")).thenReturn("MockTitle");
        assertTrue(validation.movieExists(new Movie(1, "MockTitle", null, null, "2001", new ArrayList<>())));
    }

    @Test
    public void testIfMovieDoesNotExist(){
        assertFalse(validation.movieExists(new Movie(1, "MockTitle", null, null, "2001", new ArrayList<>())));
    }

    @Test
    public void testIfStringIsValid(){
        assertTrue(validation.validString("something"));
    }

    @Test
    public void testIfStringIsNotValid(){
        assertFalse(validation.validString("stringofwffffffffffffffffffffffffffffffffffffffffffffffffffff"));
    }
}
