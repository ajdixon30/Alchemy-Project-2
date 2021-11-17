package com.Revature.Project2.beans.controllers;
import com.Revature.Project2.beans.pojos.Movie;
import com.Revature.Project2.services.DisplayMovies;
import com.Revature.Project2.services.GetMovies;
import com.Revature.Project2.services.ManageMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for Movie-related business logic
 */
@RestController
@RequestMapping(value = "/movie")
public class MovieController {
    private final DisplayMovies display;
    private final GetMovies get;
    private final ManageMovies manage;

    @Autowired
    public MovieController(DisplayMovies display, GetMovies get, ManageMovies manage) {
        this.display = display;
        this.get = get;
        this.manage = manage;
    }

    /**
     * Creates new movie in DB
     * @param movie Requires a movie object from the UI
     * @return ResponseEntity initialized with addNewMovie provided HttpStatus
     */
    @CrossOrigin
    @PostMapping(value = "/newMovie", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> newMovie(@RequestBody Movie movie){//TODO: Clean up controller
        System.out.println("I can get here!!!");
        return new ResponseEntity(get.addNewMovie(movie.getTitle()));
    }

    /**
     * Filters all of current movies depending on the filter keyword
     * @param filter keyword provided by the request parameter
     * @param value keyword provided by the request parameter
     * @return a List of movies related to the provided filter
     */
    @CrossOrigin
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> filterMovie(@RequestParam String filter, String value) {
        return display.filterMovies(filter, value);
    }

    /**
     * Displays all movies
     * @return List of all movies
     */
    @CrossOrigin
    @GetMapping(value = "/display", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> displayAll() {
        return display.displayAllMovies();
    }

    /**
     * Removes a movie from the database
     * @param id requires the movie id.
     */
    @CrossOrigin
    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteMovie(@RequestParam Integer id) {
        ManageMovies.deleteMovie(id);
    }
}
