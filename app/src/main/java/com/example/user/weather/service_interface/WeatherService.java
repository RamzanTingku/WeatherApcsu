package com.example.user.weather.service_interface;

import com.example.user.weather.apcsu.Example;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by user on 18/9/2017.
 */

public interface WeatherService {


//    String BASE_URL = "http://api.apixu.com/v1/";
//    @GET("forecast.json?key=28094519d2bf40e3b81161108172309&q=dhaka&days=2")
//    Call<Example> getWeather();

    String BASE_URL = "http://api.apixu.com/v1/";
   @GET("forecast.json?key=28094519d2bf40e3b81161108172309&q=90.000000,-30.000000&days=1")
   Call<Example> getWeather();


    class Factory {

        private static WeatherService service;
        public static WeatherService getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
                service = retrofit.create(WeatherService.class);
                return service;
            }
            else {
                return service;
            }
        }
    }



}
