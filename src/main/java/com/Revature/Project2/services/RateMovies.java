package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.Movie;
import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.repos.MovieRepo;
import com.Revature.Project2.repos.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class RateMovies {
    private HttpStatus status;
    private Validation validation = new Validation();
    private final RatingRepo ratingRepo;
    private final MovieRepo movieRepo;

    @Autowired
    public RateMovies(RatingRepo ratingRepo, MovieRepo movieRepo) {
        this.ratingRepo = ratingRepo;
        this.movieRepo = movieRepo;
    }

    //Adds a new rating to the Rating table
    public void rateMovie(RateMovies rateMovies, Movie movie, User user){
        //TODO: Write this method


    }

    public void updateRating(){
        //TODO: Write this method maybe
    }

    public void removeRating(){
        //TODO: Write this method maybe
    }
}
