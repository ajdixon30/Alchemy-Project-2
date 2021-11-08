package com.Revature.Project2.beans.controllers;

import com.Revature.Project2.beans.pojos.Movie;
import com.Revature.Project2.services.DisplayMovies;
import com.Revature.Project2.services.GetMovies;
import com.Revature.Project2.services.ManageMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

//    @GetMapping(value = "/all-movies", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    //public String get() {
        //return display.displayAllMovies();
    //}
    @PostMapping(value = "/newMovie", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Movie newMovie(@RequestParam String title){
        Movie movie = new Movie();
        List<String> props;
        String id = get.getImdbId(title);
        props = get.getMovieById(id);
        movie.setTitle(props.get(0));
        movie.setGenre(props.get(1));
        try {
            manage.saveMovie(movie);//Saves the newly created movie object
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return movie;
    }
    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void filterMovie(@RequestParam String filter, String value) {
        List<String> movieID;
        //movieID = display.filterMovies(filter, value);
        //return movieID;
    }
    @GetMapping(value = "/display", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<List> displayAll() {
        List<List> movie;
        movie = display.displayAllMovies();
        return movie;
    }
}
