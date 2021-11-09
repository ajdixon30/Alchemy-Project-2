package com.Revature.Project2.services;
import com.Revature.Project2.beans.pojos.Movie;
import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.repos.MovieRepo;
import com.Revature.Project2.repos.RatingRepo;
import com.Revature.Project2.repos.RequestRepo;
import com.Revature.Project2.repos.UserRepo;
import org.springframework.stereotype.Component;


//TODO: Decide if we want to make this class a bean or keep it as a Util class
@Component
public class Validation {
    private final UserRepo userRepo;
    private final MovieRepo movieRepo;
    private final RatingRepo ratingRepo;
    private final RequestRepo requestRepo;

    public Validation(UserRepo userRepo, MovieRepo movieRepo, RatingRepo ratingRepo, RequestRepo requestRepo) {
        this.userRepo = userRepo;
        this.movieRepo = movieRepo;
        this.ratingRepo = ratingRepo;
        this.requestRepo = requestRepo;
    }

    //Validates that the inputted username and password match a username and associated password in the database.
    public boolean validateUser(String username, String password, User user){
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }

    //Validates that the inputted first and last name are proper Names.
    public boolean validateName(String firstName, String lastName){
        boolean isRegularName = false;

        if(firstName.matches("[a-zA-Z]{2,25}$")&& lastName.matches("[a-zA-Z]{2,25}$")){
            isRegularName = true;
        } else {
            //TODO: write to FileLogger and return to registration page ("/register")
        }
        return isRegularName;
    }

    //Validates that the username and password meet the requirements for a valid username or password
    public boolean validateUserCreds(String username, String password){
        boolean isRegularCreds = false;

        if(password.matches("[a-zA-Z0-9]{2,25}$")&& username.matches("[a-zA-Z0-9]{2,25}$")){
            isRegularCreds = true;
        } else {
            //TODO: write to FileLogger and return to registration page ("/register")
        }
        return isRegularCreds;
    }

    //Validates that the given string is not empty and fits within the set size range (1-30 characters long)
    public boolean validString(String string){
        return string.matches("[a-zA-Z ]{1,30}$");
    }


    //Validates that the user exists in the database
    public boolean userExists(String username){
        return userRepo.existsById(username);
    }

    //Validates that the request exists in the database
    public boolean requestExists(Integer id){
        return requestRepo.existsById(id);
    }

    //Validates that the movie exists in the database
    public boolean movieExists(Integer id){
        return movieRepo.existsById(id);
    }

    public boolean movieExists(Movie movie){
        boolean exists = false;
        String movies = "";
        movies = movieRepo.movieSearch(movie.getTitle());
        if (movies != null){
            exists = true;
        }
        return exists;
    }

    //Validates that the rating exists in the database
    public boolean ratingExists(Integer id){
        return ratingRepo.existsById(id);
    }

}
