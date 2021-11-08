package com.Revature.Project2.beans.controllers;

import com.Revature.Project2.beans.pojos.Rating;
import com.Revature.Project2.services.RateMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.*;

@RestController
public class RatingController {
    private final RateMovies rateMovies;


    @Autowired
    public RatingController(RateMovies rateMovies) {
        this.rateMovies = rateMovies;
    }
    @GetMapping(value = "/rate-movies", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public HttpStatus getRateMovies(String title) {
        return HttpStatus.OK;
    }
    @PostMapping(value = "/rate-movies", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public HttpStatus postRating(@RequestBody Rating userRating ){
        rateMovies.rateMovie(userRating);
        return HttpStatus.CREATED;
    }
}
