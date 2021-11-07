package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.Movie;
import com.Revature.Project2.beans.pojos.Rating;
import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.repos.MovieRepo;
import com.Revature.Project2.repos.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class RateMovies {
    private HttpStatus status;
    private final Validation validation;
    private final RatingRepo ratingRepo;
    private final MovieRepo movieRepo;

    @Autowired
    public RateMovies(Validation validation, RatingRepo ratingRepo, MovieRepo movieRepo) {
        this.validation = validation;
        this.ratingRepo = ratingRepo;
        this.movieRepo = movieRepo;
    }

    //Adds a new rating to the Rating table
    public HttpStatus rateMovie(Rating rating){

        if(rating == null){
            return HttpStatus.NOT_ACCEPTABLE;
        }

        if(validation.movieExists(rating.getMovie().getId()) && validation.userExists(rating.getUser().getUsername())){
            /*TODO: Write if statement. Call validation method that checks if the user has already rated the movie.
            Do this by checking if user.getRatingsByUser and movie.getRatingsByMovie contain the same rating id
            If the user has already rated this movie, call updateRating.
            */
            ratingRepo.save(rating);
            status = HttpStatus.ACCEPTED;
        } else{
            status = HttpStatus.NOT_FOUND;
        }
        return  status;
    }

    public void updateRating(){
        //TODO: Write this method maybe
    }

    public void removeRating(){
        //TODO: Write this method maybe
    }
}
