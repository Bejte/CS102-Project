package com.example.catastrophecompass.DataLayer.Model;

import java.util.LinkedHashMap;

public class WorkplaceWeather {

    private String currentTemp, currentWeatherType;
    private LinkedHashMap<String, String> hour;

    public WorkplaceWeather(String currentTemp, String currentWeatherType, LinkedHashMap<String, String> hour) {
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

    public LinkedHashMap<String, String> getHour() {
        return hour;
    }

    public void setHour(LinkedHashMap<String, String> hour) {
        this.hour = hour;
    }
}
