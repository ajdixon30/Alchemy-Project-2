package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.Movie;
import com.Revature.Project2.repos.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManageMovies {
    private static MovieRepo repo;

    @Autowired
    public ManageMovies(MovieRepo repo) {
        this.repo = repo;
    }

    public static void saveMovie(Movie movie){
        repo.save(movie);
        //TODO: Write this method
    }

    public static void deleteMovie(Movie movie){
        repo.delete(movie);
        //TODO: Write this method
    }

    public static void updateMovie(){
        //TODO: Write this method maybe
    }
}
