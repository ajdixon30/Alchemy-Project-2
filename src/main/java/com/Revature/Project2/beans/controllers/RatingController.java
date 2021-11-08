package com.Revature.Project2.beans.controllers;

import com.Revature.Project2.beans.pojos.Rating;
import com.Revature.Project2.services.RateMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.*;

@RestController
public class RatingController {
    private final RateMovies movieRating;


    @Autowired
    public RatingController(RateMovies movieRating) {
        this.movieRating = movieRating;
    }

    @GetMapping(value = "/rate-movies", produces = APPLICATION_JSON_VALUE)
    public HttpStatus getMoviesRating(@RequestParam String id) {
        return HttpStatus.OK;
    }

    @PostMapping(value = "/rate-movies", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public HttpStatus postRating(@RequestBody Rating userRating ){
        return  movieRating.rateMovie(userRating);
    }
}
