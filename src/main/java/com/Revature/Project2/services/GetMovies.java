package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//This class interacts with the 3rd party API to get the movies.
@Service
@Transactional
public class GetMovies {
    private String APIKey;
    private Validation validation;

    @Autowired
    public GetMovies(Validation validation) {
        this.validation = validation;
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

    public HttpStatus addNewMovie(String title){
        OkHttpClient client = new OkHttpClient();
        Movie movie = new Movie();

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
            String resp = response.body().string().substring(11);

            //Converts the string of results into an array of JSON objects
            JSONArray results = new JSONArray(resp);

            //Iterates through the array of results
            if (results.length() > 0) {
                for (int i = 0; i < results.length(); i++) {
                    //Searches to find the object which contains the title within the HTTP Request
                    if ((results.getJSONObject(i).getString("title").equalsIgnoreCase(title))) {
                        //Grabs the appropriate result from the response
                        JSONObject json = results.getJSONObject(i);
                        id = json.getString("imdb_id");
                        movie.setTitle(json.getString("title"));
                    }
                }
            } else {
                id = null;
            }

        } catch (IOException | JSONException | NullPointerException e) {
            e.printStackTrace();
            //TODO: Add File Logger
        }
        if (id == null || validation.movieExists(movie)){//No results from the third party API or the movie already exists
            return HttpStatus.BAD_REQUEST;
        } else {

            //Send another request using the movie id to grab movie properties
            request = new Request.Builder()
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
                title = movieInfo.getString("title");
                movie.setTitle(title);

                //Serializes the genre section of the JSON into an array, then a JSON object
                JSONArray genreInfo = movieInfo.optJSONArray("gen");
                JSONObject genrePortion = genreInfo.optJSONObject(0);
                String genre = genrePortion.getString("genre");
                movie.setGenre(genre);
            } catch (IOException e) {
                e.printStackTrace();
                //TODO: Add File Logger
            }
            ManageMovies.saveMovie(movie);
            return HttpStatus.CREATED;
        }
    }

//    public void populateMovieTable(){
//        movies.add("The Godfather");
//        movies.add("The Dark Knight");
//        movies.add("Scarface");
//        movies.add("The Last Airbender");
//        movies.add("Shrek");
//        for (String movie: movies) {
//            Movie newMovie = new Movie();
//            String id = getImdbId(movie);
//            movieProps = getMovieById(id);
//            newMovie.setTitle(movieProps.get(0));
//            newMovie.setGenre(movieProps.get(1));
//            ManageMovies.saveMovie(newMovie);
//        }
//    }

}
