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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//Methods for displaying list of movies
@Service
@Transactional
public class DisplayMovies {
    private String APIKey;
    List<String> movieID = new ArrayList<>();
    GetMovies getMovie = new GetMovies();
    List<List> titleGenre = new ArrayList<>();
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
    public List<List> displayAllMovies() {
        OkHttpClient client = new OkHttpClient();

        final String byYearUrl = "https://data-imdb1.p.rapidapi.com/movie/byYear/2021/?page_size=10";
        String movie = null;

        Request request = new Request.Builder()
                .url(byYearUrl)
                .get()
                .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", APIKey)
                .build();

        for(int i = 0; i < 10; i++) {
            try {
                Response response = client.newCall(request).execute();
                movie = response.body().string().substring(108);
                JSONArray json = new JSONArray(movie);
                movieID.add(json.getJSONObject(i).getString("imdb_id"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for(int j = 0; j < movieID.size(); j++) {
            String id = movieID.get(j);
            titleGenre.add(getMovie.getMovieById(id));
        }
        return titleGenre;
    }

    public List<String> filterMovies(String filter, String value) {
        String movie = null;
        OkHttpClient client = new OkHttpClient();
        switch (filter) {
            case "genre":
                Request request = new Request.Builder()
                        .url("https://data-imdb1.p.rapidapi.com/movie/byGen/" + value + "/?page_size=10")
                        .get()
                        .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                        .addHeader("x-rapidapi-key", APIKey)
                        .build();
                try {
                    for (int i = 0; i < 10; i++) {
                        Response response = client.newCall(request).execute();
                        movie = response.body().string().substring(109);
                        JSONArray json = new JSONArray(movie);
                        movieID.add(json.getJSONObject(i).getString("title"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "year":

                request = new Request.Builder()
                        .url("https://data-imdb1.p.rapidapi.com/movie/byYear/" + value + "/?page_size=10")
                        .get()
                        .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                        .addHeader("x-rapidapi-key", APIKey)
                        .build();
                for(int i = 0; i < 10; i++) {
                    try {
                        Response response = client.newCall(request).execute();
                        movie = response.body().string().substring(108);
                        JSONArray json = new JSONArray(movie);
                        movieID.add(json.getJSONObject(i).getString("title"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "keyword":
                request = new Request.Builder()
                        .url("https://data-imdb1.p.rapidapi.com/movie/byKeywords/" + value + "/?page_size=10")
                        .get()
                        .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                        .addHeader("x-rapidapi-key", "77a26b5fc8mshb33e4fc6e9dd843p1c3631jsn82798791ed4a")
                        .build();

                try {
                    for(int i = 0; i < 10; i++) {
                        Response response = client.newCall(request).execute();
                        movie = response.body().string().substring(114);
                        JSONArray json = new JSONArray(movie);
                        movieID.add(json.getJSONObject(i).getString("title"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(movieID);
                break;
            case "rating":
                request = new Request.Builder()
                        .url("https://data-imdb1.p.rapidapi.com/movie/byContentRating/" + value + "/?page_size=10")
                        .get()
                        .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                        .addHeader("x-rapidapi-key", "77a26b5fc8mshb33e4fc6e9dd843p1c3631jsn82798791ed4a")
                        .build();

                try {
                    for(int i = 0; i < 10; i++) {
                        Response response = client.newCall(request).execute();
                        movie = response.body().string().substring(115);
                        JSONArray json = new JSONArray(movie);
                        movieID.add(json.getJSONObject(i).getString("title"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(movieID);
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
