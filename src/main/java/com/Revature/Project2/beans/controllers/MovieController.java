package com.Revature.Project2.beans.controllers;
import com.Revature.Project2.beans.pojos.Movie;
import com.Revature.Project2.services.DisplayMovies;
import com.Revature.Project2.services.GetMovies;
import com.Revature.Project2.services.ManageMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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

    @PostMapping(value = "/newMovie", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newMovie(@RequestParam String title){//TODO: Clean up controller
        return new ResponseEntity(get.addNewMovie(title));
    }
    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<String> filterMovie(@RequestParam String filter, String value) {
        List<String> movieID;
        movieID = display.filterMovies(filter, value);
        return movieID;
    }
    @GetMapping(value = "/display", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> displayAll() {
        return display.displayAllMovies();
    }
}
