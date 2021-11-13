package com.Revature.Project2.services;

import com.Revature.Project2.CineFile;
import com.Revature.Project2.repos.MovieRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;


public class GetMoviesTest {
    /*
        Test: We want to test the addNewMovie method to see if the given movie
        title will return the expected HTTP Status code

        SUT: (our unit) the addNewMovie method in the GetMovies Class
        addNewMovie(String title)
     */
    private GetMovies sut;
    private MovieRepo repo;

    ConfigurableApplicationContext context = SpringApplication.run(CineFile.class);

    @Before
    public void before(){
        repo = context.getBean(MovieRepo.class);
        sut = context.getBean(GetMovies.class);
    }

    @After
    public void after(){
        repo.deleteMovie("Scarface");
    }

    @Test
    public void Test_goodMovieSearchReturnsCreatedHttpStatus(){
        //Arrange - Taken care of above
        //Act
        //Assert
        Assert.assertEquals(HttpStatus.CREATED, sut.addNewMovie("Scarface"));
    }

    @Test
    public void Test_badMovieSearchReturnsBadRequestHttpStatus(){
        //Arrange - Taken care of above
        //Act
        //Assert
        Assert.assertEquals(HttpStatus.BAD_REQUEST, sut.addNewMovie("Scurface"));
    }

    @Test
    public void Test_ifMovieExistsReturnsBadRequestHttpStatus(){
        //Arrange - Taken care of above
        sut.addNewMovie("Scarface");
        // Act
        //Assert
        Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, sut.addNewMovie("Scarface"));
    }
}
