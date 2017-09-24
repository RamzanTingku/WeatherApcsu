package com.example.user.weather.apcsu;

import com.example.user.weather.google_pid_model.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by user on 23/9/2017.
 */

public interface ApiTool {
//    @GET("api/place/details/json?sensor=true&key=AIzaSyDN7RJFmImYAca96elyZlE5s_fhX-MMuhk")
//    Call<com.example.user.weather.google_pid_model.Example> getLocation(@Query("place_id") String place_id);


    String BASE_URL = "http://api.apixu.com/v1/";
    @GET("forecast.json?key=28094519d2bf40e3b81161108172309&days=7")
    Call<Example> getAWeather(@Query("q")List<Double> loc);

}
