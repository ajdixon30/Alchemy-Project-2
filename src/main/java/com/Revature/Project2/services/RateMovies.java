package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.Movie;
import com.Revature.Project2.beans.pojos.Rating;
import com.Revature.Project2.repos.MovieRepo;
import com.Revature.Project2.repos.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;

@Service
@Transactional
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
        if (validation.movieExists(rating.getMovie().getId()) && validation.userExists(rating.getUser().getUsername())){
            if (rating.getId() != null) {
                if (validation.ratingExists(rating.getId())) {
                    return updateRating(rating);
                }
                return HttpStatus.NOT_FOUND;
            } else {
                ratingRepo.save(rating);
                status = HttpStatus.CREATED;
            }
        }
        else {
            status = HttpStatus.NOT_FOUND;
        }
        return status;
    }

    //Updates a rating
    public HttpStatus updateRating(Rating rating){
        Rating r = ratingRepo.getById(rating.getId());
        if (rating.getRating() == r.getRating()){return HttpStatus.ACCEPTED;}
        r.setRating(rating.getRating());
        return HttpStatus.OK;
    }

    //Deletes a rating from the database
    public HttpStatus removeRating(Rating rating){
        if (rating.getId() != null){
            if(validation.ratingExists(rating.getId())) {
                ratingRepo.delete(rating);
                return HttpStatus.OK;
            }else{
                return HttpStatus.NOT_FOUND;
            }
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    //Gets one rating
    public Rating getOneRating(int id){
        return ratingRepo.getById(id);
    }

    //Gets the average rating
    public String getAvgRating(Integer id){
        Movie movie = movieRepo.getById(id);
        DecimalFormat df = new DecimalFormat("0.00");
        double average = 0.00d;
        List<Rating> list = movie.getRatingsByMovie();
        for (Rating rating : list) {
            average += rating.getRating();
        }
        average = average/list.size();

        return df.format(average);
    }
}
