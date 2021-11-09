package com.Revature.Project2.beans.controllers;

import com.Revature.Project2.beans.pojos.Rating;
import com.Revature.Project2.services.RateMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("rate-movies")
public class RatingController {
    private final RateMovies movieRating;


    @Autowired
    public RatingController(RateMovies movieRating) {
        this.movieRating = movieRating;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getMoviesRating(@RequestParam String id) {
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * posts a new rating for the specified movie
     * @param userRating
     * @return HttpStatus depending on the result of rateMovie()
     */
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity postRating(@RequestBody Rating userRating){
        return new ResponseEntity(movieRating.rateMovie(userRating));
    }

    @DeleteMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity deleteRating(@RequestBody Rating toBeDeleted){
        return new ResponseEntity(movieRating.removeRating(toBeDeleted));
    }
}
