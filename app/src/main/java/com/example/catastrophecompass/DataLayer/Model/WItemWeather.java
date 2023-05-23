package com.example.catastrophecompass.DataLayer.Model;

public class WItemWeather {

    private String currentTemp, currentWeatherType;

    public WItemWeather(String currentTemp, String currentWeatherType) {
        this.currentTemp = currentTemp;
        this.currentWeatherType = currentWeatherType;
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
}
