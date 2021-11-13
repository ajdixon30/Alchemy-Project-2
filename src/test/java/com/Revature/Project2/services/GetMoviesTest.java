package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.Movie;
import com.Revature.Project2.repos.MovieRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class GetMoviesTest {
    private GetMovies sut;
    private MovieRepo repo;

    @Autowired
    public GetMoviesTest(GetMovies sut, MovieRepo repo) {
        this.sut = sut;
        this.repo = repo;
    }

    @AfterEach
    public void after(){
        repo.deleteMovie("Scarface");
    }

    @Test
    public void Test_goodMovieSearchReturnsCreatedHttpStatus(){
        //Arrange - Taken care of above
        //Act
        //Assert
        Assertions.assertEquals(HttpStatus.CREATED, sut.addNewMovie("Scarface"));
    }

    @Test
    public void Test_badMovieSearchReturnsBadRequestHttpStatus(){
        //Arrange - Taken care of above
        //Act
        //Assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, sut.addNewMovie("Scurface"));
    }

    @Test
    public void Test_ifMovieExistsReturnsBadRequestHttpStatus(){
        //Arrange
        //Movie - Integer id, String, title, String genre, String picture_id, String year
        Movie movie = new Movie();
        movie.setTitle("Scarface");
        movie.setYear("1984-04-05");
        movie.setGenre("Crime");
        movie.setPicture_id("https://m.media-amazon.com/images/M/MV5BNjdjNGQ4NDEtNTEwYS00MTgxLTliYzQtYzE2ZDRiZjFhZmNlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg");
        // Act
        repo.save(movie);
        //Assert
        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE, sut.addNewMovie("Scarface"));
    }

    //TODO: Tests for exceptions (NullPointer, IO, JSONException)
}
