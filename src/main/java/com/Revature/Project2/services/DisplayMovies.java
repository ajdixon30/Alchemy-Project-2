package com.Revature.Project2.services;

import com.Revature.Project2.repos.MovieRepo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

//Methods for displaying list of movies
@Service
@Transactional
public class DisplayMovies {

    public void filterMovies(){
        //TODO: Write this method; call methods from the FilterMovies class; may merge FilterMovies with this class
    }


    //BONUS STORIES
    public void displaySynopsis(){
        //TODO: Write this method if we have time to complete the bonus stories
    }

    public void displayUpcomingMovies(){
        //TODO: Write this method if we have time to complete the bonus stories
    }
}
