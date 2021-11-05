package com.Revature.Project2.services;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//This class interacts with the 3rd party API to get the movies.
public class GetMovies {

    public void getMovie(){
        //TODO: Write this method
        Response response;
        JSONArray jsa;

        OkHttpClient client = new OkHttpClient();
        final String key = "77a26b5fc8mshb33e4fc6e9dd843p1c3631jsn82798791ed4a";

        Request request = new Request.Builder()
                .url("https://data-imdb1.p.rapidapi.com/movie/id/tt0086250/")
                .get()
                .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", key)
                .build();

        try {
            response = client.newCall(request).execute();
            String s = response.body().string().substring(11, 1642);
            System.out.println(s);
            JSONObject jso = new JSONObject(s);
            System.out.print(jso.get("title"));
//            jsa = new JSONArray(s);
//            JSONObject jso = new JSONObject(jsa.toString());
//            System.out.println();
//            System.out.println(getValuesForGivenKey(jsa, "title"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

    public List<String> getValuesForGivenKey(JSONArray jsa, String key) {
        return IntStream.range(0, jsa.length())
                .mapToObj(index -> ((JSONObject)jsa.get(index)).optString(key))
                .collect(Collectors.toList());
    }
}

    //TODO: Maybe make another method for the ManageMovies class to call????

