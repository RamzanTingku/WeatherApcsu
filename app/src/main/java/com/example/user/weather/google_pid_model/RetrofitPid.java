package com.example.user.weather.google_pid_model;

import com.example.user.weather.google_pid_model.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by user on 22/9/2017.
 */

public interface RetrofitPid {


        //https://maps.googleapis.com/maps/api/place/radarsearch/json?location=51.503186,-0.126446&radius=5000&type=museum&key=YOUR_API_KEY
    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of student.
     */

        //https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJN1t_tDeuEmsRUsoyG83frY4&key=YOUR_API_KEY
        //https://developers.google.com/places/web-service/details
        @GET("api/place/details/json?sensor=true&key=AIzaSyDN7RJFmImYAca96elyZlE5s_fhX-MMuhk")
        Call<com.example.user.weather.google_pid_model.Example> getLocation(@Query("place_id") String place_id);

    }

