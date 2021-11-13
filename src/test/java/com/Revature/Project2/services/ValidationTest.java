package com.Revature.Project2.services;

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

//@SpringBootTest
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
}
