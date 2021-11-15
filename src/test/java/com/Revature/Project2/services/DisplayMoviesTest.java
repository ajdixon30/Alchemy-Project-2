package com.Revature.Project2.services;

import com.Revature.Project2.repos.MovieRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DisplayMoviesTest {
    /*
    SUT: DisplayMovies
    Units (Methods):
        1. filterMovies (Return List<String>):
            Success (filter: "genre"):
            Valid filter is sent
            Valid value for that filter is sent
            List of movie titles are sent which match the search criteria

            Success (filter: "year"):
            Valid filter is sent
            Valid value for that filter is sent
            List of movie titles are sent which match the search criteria

            Failure (filter does not exist):
            Filter is sent
            ???

            Failure (value for filter does not exist):
            Valid filter is sent
            Invalid value for that filter is sent
            Empty list is returned

     */

    private final GetMovies getMovies;
    private final MovieRepo movieRepo;
    private final DisplayMovies sut;
    private static List<String> movieID;

    @Autowired
    public DisplayMoviesTest(GetMovies getMovies, MovieRepo movieRepo, DisplayMovies sut){
        this.getMovies = getMovies;
        this.movieRepo = movieRepo;
        this.sut = sut;
    }

    @BeforeEach
    public void storeNecessaryValues(){
        //Initialize the list to store results
        movieID = new ArrayList<>();

        //Save Movies to the Database
        getMovies.addNewMovie("Scarface");
        getMovies.addNewMovie("Get Out");
        getMovies.addNewMovie("John Wick");
        getMovies.addNewMovie("Shrek");
        getMovies.addNewMovie("Rocky");

    }

    @AfterEach
    public void deleteMovie(){
        movieRepo.deleteAll();
    }

    @Test
    public void Test_successfulSearchByGenre(){
        //Arrange
        movieID.add("Scarface");
        movieID.add("John Wick");
        //Assert
        Assertions.assertEquals(movieID, sut.filterMovies("genre", "Crime"));
    }

    @Test
    public void Test_successfulSearchByYear(){
        //Arrange
        movieID.add("Scarface");
        //Assert
        Assertions.assertEquals(movieID, sut.filterMovies("year", "1984"));
    }

    @Test
    public void Test_invalidSearchByGenre(){
        //Assert
        Assertions.assertEquals(movieID, sut.filterMovies("genre", "Comedy"));
    }

    @Test
    public void Test_invalidSearchByYear(){
        //Assert
        Assertions.assertEquals(movieID, sut.filterMovies("year", "1994"));
    }
}
