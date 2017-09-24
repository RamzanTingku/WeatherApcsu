package com.example.user.weather.data;

/**
 * Created by user on 21/9/2017.
 */

public class Weather {

    private String city;
    private String area;
    private double latitude;
    private double longitude;

    public Weather(String city) {
        this.city = city;
    }

    public Weather(String city, String area) {
        this.city = city;
        this.area = area;
    }

    public Weather(String city, String area, double latitude, double longitude) {
        this.city = city;
        this.area = area;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
