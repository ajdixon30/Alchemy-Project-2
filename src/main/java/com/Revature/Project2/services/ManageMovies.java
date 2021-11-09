package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.Movie;
import com.Revature.Project2.repos.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class ManageMovies {
    private static MovieRepo repo;
    private static Validation validation;

    @Autowired
    public ManageMovies(MovieRepo repo, Validation validation) {
        ManageMovies.validation = validation;
        ManageMovies.repo = repo;
    }

    public static Movie saveMovie(Movie movie){
        boolean exists = validation.movieExists(movie);
        String entry = "";
        Movie passThis = new Movie();
        if (!exists){
            repo.save(movie);
            passThis = movie;
        } else {
            entry = repo.movieSearch(movie.getTitle());
            String[] entryArray = entry.split(",");
            passThis.setId(Integer.parseInt(entryArray[0]));
            passThis.setGenre(entryArray[1]);
            passThis.setTitle(entryArray[2]);
        }
        //TODO: Write this method
        return passThis;
    }

    public static void deleteMovie(Movie movie){
        repo.delete(movie);
        //TODO: Write this method
    }

    public static void updateMovie(){
        //TODO: Write this method maybe
    }
}
