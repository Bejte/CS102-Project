package com.example.catastrophecompass.DataLayer.RemoteDataRepository.WeatherRepository;

public class WeatherData {
    double lat;
    double lon;
    String timezone;
    long timezone_offset;
    CurrentWeather current;
    HourlyWeather[] hourly;
    // Other properties...

    static class CurrentWeather {
        long dt;
        long sunrise;
        long sunset;
        double temp;
        double feels_like;
        int pressure;
        int humidity;
        double dew_point;
        double uvi;
        int clouds;
        int visibility;
        double wind_speed;
        int wind_deg;
        double wind_gust;
        Weather[] weather;
        // Other properties...
    }

    static class HourlyWeather {
        long dt;
        double temp;
        double feels_like;
        int pressure;
        int humidity;
        double dew_point;
        double uvi;
        int clouds;
        int visibility;
        double wind_speed;
        int wind_deg;
        double wind_gust;
        Weather[] weather;
        double pop;
        // Other properties...
    }

    static class Weather {
        int id;
        String main;
        String description;
        String icon;
    }
}
