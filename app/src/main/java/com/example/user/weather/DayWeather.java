package com.example.user.weather;

/**
 * Created by user on 19/9/2017.
 */

public class DayWeather {
    private String dTime;
    private String dTemp;
    private int dImage;

    public DayWeather(String dTime, String dTemp, int dImage) {
        this.dTime = dTime;
        this.dTemp = dTemp;
        this.dImage = dImage;
    }

    public String getdTime() {
        return dTime;
    }

    public String getdTemp() {
        return dTemp;
    }

    public int getdImage() {
        return dImage;
    }

    public void setdTime(String dTime) {
        this.dTime = dTime;
    }

    public void setdTemp(String dTemp) {
        this.dTemp = dTemp;
    }

    public void setdImage(int dImage) {
        this.dImage = dImage;
    }
}
