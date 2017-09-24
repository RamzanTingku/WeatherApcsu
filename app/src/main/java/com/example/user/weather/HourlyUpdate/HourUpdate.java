package com.example.user.weather.HourlyUpdate;

import java.util.ArrayList;

/**
 * Created by RamzanUllah on 25-Sep-17.
 */

    public class HourUpdate {
    private String time;
        private String temp;
    private String conditionText;
    private int conditionIcon;


    public HourUpdate() {
    }

    public HourUpdate(String time, String temp, int icon) {
        this.time = time;
        this.temp = temp;
        this.conditionIcon = icon;
    }

    public HourUpdate(String time, String temp, String conditionText) {
        this.time = time;
        this.temp = temp;
        this.conditionText = conditionText;
    }

    public String getTime() {
        return time;
    }

    public String getTemp() {
        return temp;
    }

    public String getConditionText() {
        return conditionText;
    }

    public int getConditionIcon() {
        return conditionIcon;
    }




}
