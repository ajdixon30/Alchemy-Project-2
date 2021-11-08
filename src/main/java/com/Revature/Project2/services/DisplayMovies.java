package com.Revature.Project2.services;


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
import java.util.Properties;

//Methods for displaying list of movies
@Service
@Transactional
public class DisplayMovies {
    private String APIKey;
    //public final MovieRepo movieRepo;

//    public DisplayMovies(MovieRepo movieRepo) {
//        this.movieRepo = movieRepo;
    //}


    public DisplayMovies() {
        try {
            Properties props = new Properties();
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            InputStream fileIn = cl.getResourceAsStream("application.properties");
            props.load(fileIn);
            APIKey = props.getProperty("APIKey");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //This method displays all available movies
    public void displayAllMovies(){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://data-imdb1.p.rapidapi.com/movie/id/tt0086250/")
                .get()
                .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", APIKey)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String resp = response.body().string().substring(11);
            JSONObject json = new JSONObject(resp);
            String title = json.getString("title");
            int year = json.getInt("year");
            System.out.println(title);
            System.out.println(year);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        final String byYearUrl = "https://data-imdb1.p.rapidapi.com/movie/byYear/2021/?page_size=50";
//
//        List<String> movies = null;
//        String movie = null;
//
//        Request request = new Request.Builder()
//                .url(byYearUrl)
//                .get()
//                .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
//                .addHeader("x-rapidapi-key", key)
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            movie = response.body().string();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        return movies;
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
