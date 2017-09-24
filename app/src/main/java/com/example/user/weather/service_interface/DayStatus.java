package com.example.user.weather.service_interface;

import com.example.user.weather.yahoo_weather.Yweather;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by user on 18/9/2017.
 */

public interface DayStatus {
double Latitude=40.741895;
 double  Longitude=-73.989308;

   //String query2= "select * from weather.forecast where woeid in (SELECT woeid FROM geo.places WHERE text="(latitude,longitude)")
   //String url = "select%20woeid%20from%20geo.places%20where%20text%3D%22(" + Latitude + "," + Longitude + ")%22%20limit%201&diagnostics=false";

    String city = "dhaka";
    String query = "select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+ city +"%22)&format=json";

    String BASE_URL = "https://query.yahooapis.com/v1/public/";
    @GET("yql?q="+query)
    Call<Yweather> getWeather();



    class Factory {

        private static DayStatus service;
        public static DayStatus getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
                service = retrofit.create(DayStatus.class);
                return service;
            }
            else {
                return service;
            }
        }
    }

}
