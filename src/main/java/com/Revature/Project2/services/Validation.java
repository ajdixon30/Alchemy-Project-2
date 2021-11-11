package com.Revature.Project2.services;
import com.Revature.Project2.beans.pojos.Movie;
import com.Revature.Project2.beans.pojos.User;
import com.Revature.Project2.repos.MovieRepo;
import com.Revature.Project2.repos.RatingRepo;
import com.Revature.Project2.repos.RequestRepo;
import com.Revature.Project2.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Service layer class for checking validity of varying objects
 */
@Lazy @Service
public class Validation {
    private final UserRepo userRepo;
    private final MovieRepo movieRepo;
    private final RatingRepo ratingRepo;
    private final RequestRepo requestRepo;

    @Autowired
    public Validation(UserRepo userRepo, MovieRepo movieRepo, RatingRepo ratingRepo, RequestRepo requestRepo) {
        this.userRepo = userRepo;
        this.movieRepo = movieRepo;
        this.ratingRepo = ratingRepo;
        this.requestRepo = requestRepo;
    }

    /**
     * Checks for user login accuracy
     * @param username provided username
     * @param password provided password
     * @return true if the pair of Strings matches and false otherwise
     */
    public boolean validateUser(String username, String password){
        User user = userRepo.getById(username);
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }

    /**
     * Checks first and last name for letters (a-Z) and length (2-25)
     * @param firstName provided first name
     * @param lastName provided last name
     * @return true if the pair of Strings follows the rules and false otherwise
     */
    public boolean validateName(String firstName, String lastName){
        boolean isRegularName = false;

        if(firstName.matches("[a-zA-Z]{2,25}$")&& lastName.matches("[a-zA-Z]{2,25}$")){
            isRegularName = true;
        } else {
            //TODO: write to FileLogger and return to registration page ("/register")
        }
        return isRegularName;
    }

    /**
     * Check for username and password called in Register
     * @param username provided username
     * @param password provided password
     * @return true if new credentials follow the rules and false otherwise
     */
    public boolean validateUserCreds(String username, String password){
        boolean isRegularCreds = false;

        if(password.matches("[a-zA-Z0-9]{2,25}$")&& username.matches("[a-zA-Z0-9]{2,25}$")){
            isRegularCreds = true;
        } else {
            //TODO: write to FileLogger and return to registration page ("/register")
        }
        return isRegularCreds;
    }

    /**
     * Checks if provided string fits the specified rule set
     * @param string provided string
     * @return true if string fits the rule set. false otherwise
     */
    public boolean validString(String string){
        return string.matches("[a-zA-Z1-9 ]{1,30}$");
    }


    /**
     * Checks if User exists in DB
     * @param username
     * @return true if user exists in db or false
     */
    public boolean userExists(String username){
        return userRepo.existsById(username);
    }

    /**
     * Checks if Request exists in DB
     * @param id
     * @return true is Request exists in DB or false
     */
    public boolean requestExists(Integer id){
        return requestRepo.existsById(id);
    }

    /**
     * checks if Movie exists in DB
     * @param id identifier for Movie
     * @return true if movie exists in DB or false
     */
    public boolean movieExists(Integer id){
        return movieRepo.existsById(id);
    }

    /**
     * checks if Movie exists in DB using a movie object
     * @param movie movie to be checked for
     * @return true if movie exists and false otherwise
     */
    public boolean movieExists(Movie movie){
        boolean exists = false;
        String movies = "";
        movies = movieRepo.movieSearch(movie.getTitle());
        if (movies != null){
            exists = true;
        }
        return exists;
    }

    /**
     * Checks if Rating exists in DB
     * @param id identifier for desired Rating
     * @return true if Rating exists and false otherwise
     */
    public boolean ratingExists(Integer id){
        return ratingRepo.existsById(id);
    }

    /**
     * Checks if the Request table contains any row
     * @return Return true if the table is not empty and false if it is empty.
     */
    public boolean requestNotEmpty(){
        return requestRepo.count() > 0;
    }

}
