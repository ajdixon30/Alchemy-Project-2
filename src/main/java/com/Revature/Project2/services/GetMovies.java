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
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

//This class interacts with the 3rd party API to get the movies.
@Service
@Transactional
public class GetMovies {
    private String APIKey;
    private Validation validation;

    @Autowired
    public GetMovies(Validation validation) {this.validation = validation;}

    public void acquireAPIKey(){
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

    /**
     * This method is used add movies to our database from the third party API
     * This method sends two GET requests to the API:
     *      1. A GET Request with the title of the movie that we want
     *      We receive a response containing the ID of our desired movie
     *      2. A GET Request with the ID received in the first request
     *      We receive a response containing details about the movie
     *
     * After receiving the movie details from the second GET Request, we
     * grab the necessary movie details - title and genre - and use them to
     * create a new Movie object
     *
     * Validation takes place to see if the response from the first GET Request
     * is empty, or if the movie already exists in the database
     * If either condition resolves to true, the method returns an HTTP Status of "Bad Request"
     *
     * If both conditions resolve to false, the newly created Movie object is saved in the
     * database, and the method returns an HTTP Status of "Created"
     * @param title
     * @return HttpStatus
     */
    public HttpStatus addNewMovie(String title){
        acquireAPIKey();

        OkHttpClient client = new OkHttpClient();

        Movie movie = new Movie();
        movie.setTitle(title);

        if (validation.movieExists(movie)){//Checks if an entry of this movie already exists within the database
            return HttpStatus.BAD_REQUEST;
        }

        String id = "";

        //Request is sent to the API to search for the movie by title
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
                        //Grabs the movie ID from the response body
                        id = json.getString("imdb_id");
                    }
                }
            } else {
                id = null;
            }
        } catch (IOException | JSONException | NullPointerException e) {
            e.printStackTrace();
            //TODO: Add File Logger
        }
        if (id == null){//No results from the third party API or the movie already exists
            return HttpStatus.BAD_REQUEST;
        } else {

            //Send another request to the API to find more details about the requested movie
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

    /**
     * This method is used add twenty movies to our database from the third party API
     *
     * Each of the movie titles are added to a list, and the list is iterated through
     * using a foreach loop
     *
     * Each element of the list is sent as a parameter to be used in the addNewMovie method
     *
     * After every movie title has been sent through the addNewMovie method, there will be
     * twenty movie entries within out database
     */
    public void populateMovieTable(){
        List<String> movies = new LinkedList<>();
        movies.add("The Godfather");
        movies.add("The Dark Knight");
        movies.add("Scarface");
        movies.add("The Last Airbender");
        movies.add("Shrek");
        movies.add("Pulp Fiction");
        movies.add("Creed");
        movies.add("Avengers: Endgame");
        movies.add("Rocky");
        movies.add("American Psycho");
        movies.add("Jaws");
        movies.add("1917");
        movies.add("Get Out");
        movies.add("Venom");
        movies.add("The Matrix");
        movies.add("Fight Club");
        movies.add("John Wick");
        movies.add("Fast & Furious");
        movies.add("The Amazing Spider-Man 2");
        movies.add("Knives Out");
        for (String movie: movies) {
            addNewMovie(movie);
        }
    }

}
