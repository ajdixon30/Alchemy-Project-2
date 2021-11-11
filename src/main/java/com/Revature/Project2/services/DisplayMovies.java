package com.Revature.Project2.services;


import com.Revature.Project2.beans.pojos.Movie;
import com.Revature.Project2.repos.MovieRepo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//Methods for displaying list of movies
@Service
@Transactional
public class DisplayMovies {
    private String APIKey;
    List<String> movieID = new ArrayList<>();
    List<Movie> movies = new ArrayList<>();
    List<List> titleGenre = new ArrayList<>();
    private final MovieRepo movieRepo;
    private final DatabaseLogger logger;

    @Autowired
    public DisplayMovies(MovieRepo movieRepo, DatabaseLogger logger) {
        this.movieRepo = movieRepo;
        this.logger = logger;
    }

    //This method displays all available movies
    public List<Movie> displayAllMovies() {
        return movieRepo.findAll();
    }

    public void acquireAPIKey(){
        try {
            Properties props = new Properties();
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            InputStream fileIn = cl.getResourceAsStream("application.properties");
            props.load(fileIn);
            APIKey = props.getProperty("APIKey");
        } catch (IOException e) {
            logger.writeLog("IOException found in acquireAPIKey.", 3);;
        }
    }
    public List<String> filterMovies(String filter, String value) {
        acquireAPIKey();
        String movie = null;
        movieID.clear();
        int count = 0;
        OkHttpClient client = new OkHttpClient();
        switch (filter) {
            case "genre":
                movieID = movieRepo.filterGenre(value);
                break;
            case "year":
                Integer ParsedValue = Integer.parseInt(value);
                movieID = movieRepo.filterYear(ParsedValue);
                break;
            case "keyword":
                Request request = new Request.Builder()
                        .url("https://data-imdb1.p.rapidapi.com/movie/byKeywords/" + value + "/?page_size=10")
                        .get()
                        .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                        .addHeader("x-rapidapi-key", APIKey)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    movie = response.body().string();
                    JSONObject json = new JSONObject(movie);
                    JSONArray innerJson = json.getJSONArray("results");
                    for(int i = 0; i < innerJson.length()- 1; i++) {
                        movieID.add(innerJson.getJSONObject(i).getString("title"));
                    }
                } catch (IOException e) {
                    logger.writeLog("IOException found in keyword case.", 3);
                }
                break;
            case "rating":
                request = new Request.Builder()
                        .url("https://data-imdb1.p.rapidapi.com/movie/byContentRating/" + value + "/?page_size=10")
                        .get()
                        .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                        .addHeader("x-rapidapi-key", APIKey)
                        .build();

                try {
                    for(int i = 0; i < 10; i++) {
                        count = 0;
                        Response response = client.newCall(request).execute();
                        for (int j = 0; j < value.length(); j++) {
                            count++;
                        }
                        movie = response.body().string().substring(114 + count);
                        JSONArray json = new JSONArray(movie);
                        movieID.add(json.getJSONObject(i).getString("title"));
                    }
                } catch (IOException e) {
                    logger.writeLog("IOException found in rating case.", 3);
                }
                break;
        }
        return movieID;
    }
    //BONUS STORIES
    public void displaySynopsis(){
        //TODO: Write this method if we have time to complete the bonus stories
    }

    public void displayUpcomingMovies(){
        //TODO: Write this method if we have time to complete the bonus stories
    }
  
}
