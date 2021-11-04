package com.Revature.Project2.services;

import com.Revature.Project2.repos.MovieRepo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

//Methods for displaying list of movies
@Service
@Transactional
public class DisplayMovies {
    public final MovieRepo movieRepo;

    public DisplayMovies(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Autowired
    //This method displays all available movies
    public String displayAllMovies(){
        OkHttpClient client = new OkHttpClient();
        final String key = "77a26b5fc8mshb33e4fc6e9dd843p1c3631jsn82798791ed4a";
        final String byYearUrl = "https://data-imdb1.p.rapidapi.com/movie/byYear/2021/?page_size=50";

        String movie = null;

        Request request = new Request.Builder()
                .url(byYearUrl)
                .get()
                .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", key)
                .build();

        try {
            Response response = client.newCall(request).execute();
             movie =  response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return movie;
    }

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
