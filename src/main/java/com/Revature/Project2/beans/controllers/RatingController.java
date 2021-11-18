package com.Revature.Project2.beans.controllers;

import com.Revature.Project2.beans.pojos.Rating;
import com.Revature.Project2.services.RateMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.*;

/**
 * Controller for handling Rating business logic
 */
@RestController
@RequestMapping("rate-movies")
public class RatingController {
    private final RateMovies movieRating;

    @Autowired
    public RatingController(RateMovies movieRating) {
        this.movieRating = movieRating;
    }

    /**
     * Gets a single rating using the path variable
     * @param username unique identifier for each Rating entry
     * @return rating tied to id
     */
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    @GetMapping(value = "/{username}",produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> getAllUserRating(@PathVariable String username){
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    @GetMapping(value = "/average",produces = APPLICATION_JSON_VALUE)
    public String getAverageRating(@RequestParam Integer id){
        return movieRating.getAvgRating(id);
    }

    /**
     * posts a new rating for the specified movie
     * @param userRating
     * @return HttpStatus depending on the result of rateMovie()
     */
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> postRating(@RequestBody Rating userRating){
        return new ResponseEntity(movieRating.rateMovie(userRating));
    }

    /**
     * Deletes a rating based on RequestBody
     * @param toBeDeleted rating that is soon to be deleted
     * @return ResponseEntity initialized by removeRating(
     */
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    @DeleteMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteRating(@RequestBody Rating toBeDeleted){
        return new ResponseEntity(movieRating.removeRating(toBeDeleted));
    }
}
