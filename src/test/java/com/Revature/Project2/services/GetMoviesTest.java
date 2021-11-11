package com.Revature.Project2.services;

import com.Revature.Project2.CineFile;
import org.junit.*;
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
    private String APIKey;
    private GetMovies get;
    ConfigurableApplicationContext context = SpringApplication.run(CineFile.class);

//    @BeforeClass
//    public static void beforeClass(){
//
//    }
//
//    @AfterClass
//    public static void afterClass(){
//
//    }
//
    @Before
    public void before(){
        get = context.getBean(GetMovies.class);
    }
//
//    @After
//    public static void after(){
//
//    }

    @Test
    public void Test_goodMovieSearchReturnsCreatedHttpStatus(){
        //Arrange - Taken care of above
        //Act
        //Assert
        Assert.assertEquals(get.addNewMovie("Scarface"), HttpStatus.CREATED);
    }

    @Test
    public void Test_badMovieSearchReturnsBadRequestHttpStatus(){
        //Arrange - Taken care of above
        //Act
        //Assert
        Assert.assertEquals(get.addNewMovie("Scurface"), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void Test_ifMovieExistsReturnsBadRequestHttpStatus(){
        //Arrange - Taken care of above
        // Act
        get.addNewMovie("Scarface");
        //Assert
        Assert.assertEquals(get.addNewMovie("Scarface"), HttpStatus.NOT_ACCEPTABLE);
    }
}
