package com.example.catastrophecompass.DataLayer.RemoteDataRepository.WeatherRepository;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;

import com.example.catastrophecompass.DataLayer.Model.WorkplaceWeather;
import com.google.gson.Gson;


public class WeatherAPI {
    private static final String apiUrl = "https://api.openweathermap.org/data/3.0/onecall?lat={lat}&lon={lon}&units=metric&exclude=minutely,daily,alerts";
    private static final String apiKey = "1f014062cbaec2dc6662ca1cb9d5eec5";

    public WeatherAPI(){}

    public WorkplaceWeather getWeatherInfo(String location){
        String[] loc = location.split(",");
        String lat = loc[0];
        String lon = loc[1];
        String apiUrlWithParams = apiUrl.replace("{lat}", lat).replace("{lon}", lon);
        apiUrlWithParams += "&appid=" + apiKey;

        try {
            URL url = new URL(apiUrlWithParams);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line + "\n");
                }
                reader.close();


                Gson gson = new Gson();
                WeatherData weatherData = gson.fromJson(response.toString(), WeatherData.class);
                // Access the desired properties
                // TODO test weatherDataToWorkplaceWeather
                return weatherDataToWorkplaceWeather(weatherData);

            } else {
                Log.d("WeatherAPI", "API call failed with response code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static WorkplaceWeather weatherDataToWorkplaceWeather(WeatherData data){
        HashMap<String, String> hourlyTemperature = new HashMap<>();
        WeatherData.HourlyWeather[] hourlyWeathers = data.hourly;
        String currentTemp = "" + Math.round(hourlyWeathers[0].temp);
        String currentWeatherType = hourlyWeathers[0].weather[0].description;
        currentWeatherType = currentWeatherType.substring(0,1).toUpperCase() + currentWeatherType.substring(1); // TODO uppercase all words
        for (int i = 1; i < 13; i++)
        {
            WeatherData.HourlyWeather hourly = hourlyWeathers[i];
            Date date = new Date(hourly.dt*1000);
            String hour = date.getHours() + ".00";
            String temp = Math.round(hourly.temp) + "°C";
            hourlyTemperature.put(hour, temp);
        }
        return new WorkplaceWeather(currentTemp, currentWeatherType, hourlyTemperature);
    }
}
