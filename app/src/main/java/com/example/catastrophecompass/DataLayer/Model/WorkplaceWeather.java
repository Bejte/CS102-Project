package com.example.catastrophecompass.DataLayer.Model;

import java.util.HashMap;

public class WorkplaceWeather {

    private String currentTemp, currentWeatherType;
    private HashMap<String, String> hour;

    public WorkplaceWeather(String currentTemp, String currentWeatherType, HashMap<String, String> hour) {
        this.currentTemp = currentTemp;
        this.currentWeatherType = currentWeatherType;
        this.hour = hour;
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getCurrentWeatherType() {
        return currentWeatherType;
    }

    public void setCurrentWeatherType(String currentWeatherType) {
        this.currentWeatherType = currentWeatherType;
    }

    public HashMap<String, String> getHour() {
        return hour;
    }

    public void setHour(HashMap<String, String> hour) {
        this.hour = hour;
    }
}
