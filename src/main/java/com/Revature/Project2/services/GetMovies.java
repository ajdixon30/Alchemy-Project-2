package com.Revature.Project2.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//This class interacts with the 3rd party API to get the movies.
@Service
@Transactional
public class GetMovies {
    private String APIKey;

    public GetMovies() {
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

    public String getImdbId(String title){
        OkHttpClient client = new OkHttpClient();

        String id = "";

        Request request = new Request.Builder()
                .url("https://data-imdb1.p.rapidapi.com/movie/imdb_id/byTitle/" + title + "/")
                .get()
                .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", APIKey)
                .build();

        try {
            Response response = client.newCall(request).execute();
            //Shortens the response body into a usable format
            String resp = response.body().string().substring(12);

            //Serializes the response body into a JSON object
            JSONObject json = new JSONObject(resp);
            id = json.getString("imdb_id");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            //TODO: Add File Logger
        }
        return id;
    }

    public List<String> getMovieById(String id){
        OkHttpClient client = new OkHttpClient();

        List<String> movieProps = new LinkedList<>();

        Request request = new Request.Builder()
                .url("https://data-imdb1.p.rapidapi.com/movie/id/" + id + "/")
                .get()
                .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", APIKey)
                .build();

        try {
            Response response = client.newCall(request).execute();

            //Shortens the response body into a usable format
            String resp = response.body().string().substring(11);

            //Serializes the response body into a JSON object
            JSONObject movieInfo = new JSONObject(resp);
            String title = movieInfo.getString("title");
            movieProps.add(title);

            //Serializes the genre section of the JSON into an array, then a JSON object
            JSONArray genreInfo = movieInfo.optJSONArray("gen");
            JSONObject genrePortion = genreInfo.optJSONObject(0);
            String genre = genrePortion.getString("genre");
            movieProps.add(genre);
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: Add File Logger
        }
        return movieProps;
    }

    //TODO: Figure out how to grab the genre from the JSON results
}
