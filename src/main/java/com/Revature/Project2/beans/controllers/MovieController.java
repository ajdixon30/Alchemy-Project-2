package com.Revature.Project2.beans.controllers;

import com.Revature.Project2.services.DisplayMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {
    private final DisplayMovies display;

    @Autowired
    public MovieController(DisplayMovies display) {
        this.display = display;
    }

//    @GetMapping(value = "/all-movies", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    //public String get() {
        //return display.displayAllMovies();
    //}
}
