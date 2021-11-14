package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.Movie;
import com.Revature.Project2.beans.pojos.Rating;
import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.repos.MovieRepo;
import com.Revature.Project2.repos.RatingRepo;
import com.Revature.Project2.repos.UserRepo;
import org.junit.After;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RateMoviesTest {
    /*
    SUT: RateMovies
    Units (Methods):
    1. rateMovie (Returns: HttpStatus)
        Test Success:
        Save a movie entry
        Register a User
        Create a Rating
        validate that the movie & user exist
        validate that it is a new rating - no generated ID
        save rating in database
        201 HTTP Status Code returned

        Test Failure (User does not exist):
        Save a movie entry
        Create a Rating
        Validation should fail
        404 HTTP Status Code returned

        Test Failure (Movie does not exist):
        Register a User
        Create a Rating
        Validation Should Fail
        404 HTTP Status Code Returned

        Test Failure (Rating already exists)?:

    2. updateRating (Returns: HttpStatus)
        Test
    3. removeRating (Returns: HttpStatus)
    4. getOneRating (Returns: Rating)
     */

    private final RateMovies sut;
    private final MovieRepo movieRepo;
    private final UserRepo userRepo;
    private final RatingRepo ratingRepo;
    private User user;
    private Movie movie;
    private Rating rating;

    @Autowired
    public RateMoviesTest(RateMovies sut, MovieRepo movieRepo, UserRepo userRepo, RatingRepo ratingRepo) {
        this.sut = sut;
        this.movieRepo = movieRepo;
        this.userRepo = userRepo;
        this.ratingRepo = ratingRepo;
    }

    @BeforeAll
    public void storeNecessaryValues(){
        //User object for tests
        user = new User();
        user.setUsername("adixon");
        user.setPassword("123password");
        user.setFirstName("Adam");
        user.setLastName("Dixon");

        //Movie object for tests
        movie = new Movie();
        movie.setTitle("Scarface");
        movie.setGenre("Crime");
        movie.setPicture_id("https://m.media-amazon.com/images/M/" +
                "MV5BNjdjNGQ4NDEtNTEwYS00MTgxLTliYzQtYzE2ZDRiZjFhZm" +
                "NlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg");
        movie.setYear("1984-04-05");

        //Rating object for tests
        rating = new Rating();
        rating.setRating(4);
        rating.setUser(user);
        rating.setMovie(movie);
    }

    public void clearDatabase(){
        movieRepo.deleteAll();
        userRepo.deleteAll();
        ratingRepo.deleteAll();
    }

    @Test
    @Order(1)
    public void Test_successfulRating(){
        //Save Movie to Database
        movieRepo.save(movie);
        //Save User to Database
        userRepo.save(user);
        //Assert
        Assertions.assertEquals(HttpStatus.CREATED, sut.rateMovie(rating));
    }

    @After
    public void resetDatabase(){
        clearDatabase();
    }

    @Test
    @Order(2)
    public void Test_userDoesNotExist(){
        //Save Movie to Database
        movieRepo.save(movie);
        //Assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND, sut.rateMovie(rating));
    }

    @After
    public void deleteMovie(){
        movieRepo.delete(movie);
    }

    @Test
    @Order(3)
    public void Test_ratingIsUpdated(){
        //Save Movie to Database
        movieRepo.save(movie);
        //Save User to Database
        userRepo.save(user);
        //Save Rating to Database
        ratingRepo.save(rating);
        //Act
        rating.setRating(3);
        //Assert
        Assertions.assertEquals(HttpStatus.OK, sut.rateMovie(rating));
    }

    @After
    public void resetDatabase2(){
        clearDatabase();
    }

    @Test
    @Order(4)
    public void Test_attemptToSaveTheSameRating(){
        //Save Movie to Database
        movieRepo.save(movie);
        //Save User to Database
        userRepo.save(user);
        //Save Rating to Database
        ratingRepo.save(rating);
        //Assert
        Assertions.assertEquals(HttpStatus.ACCEPTED, sut.rateMovie(rating));
    }

    @After
    public void resetDatabase3(){
        clearDatabase();
    }

    @Test
    @Order(5)
    public void Test_ratingRemovedSuccessfully(){
        //Save Movie to Database
        movieRepo.save(movie);
        //Save User to Database
        userRepo.save(user);
        //Save Rating to Database
        ratingRepo.save(rating);
        //Assert
        Assertions.assertEquals(HttpStatus.OK, sut.removeRating(rating));
    }

    @After
    public void deleteMovieAndUser(){
        movieRepo.delete(movie);
        userRepo.delete(user);
    }

    @Test
    @Order(6)
    public void Test_attemptToRemoveAnUnsavedRating(){
        //Save Movie to Database
        movieRepo.save(movie);
        //Save User to Database
        userRepo.save(user);
        //Assert
        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE, sut.removeRating(rating));
    }

    @After
    public void deleteItems(){
        movieRepo.delete(movie);
        userRepo.delete(user);
    }

    @Test
    @Order(7)
    public void Test_attemptToRemoveARatingWithNoId(){
        //Save Movie to Database
        movieRepo.save(movie);
        //Save User to Database
        userRepo.save(user);
        //Add an ID to the Rating
        rating.setId(1);
        //Assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND, sut.removeRating(rating));
    }

    @After
    public void deleteItems2(){
        movieRepo.delete(movie);
        userRepo.delete(user);
    }


}
