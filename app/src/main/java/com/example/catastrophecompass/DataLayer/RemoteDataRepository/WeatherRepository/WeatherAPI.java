package com.example.catastrophecompass.DataLayer.RemoteDataRepository.WeatherRepository;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
                // TODO convert WeatherData to WorkplaceWeather

            } else {
                Log.d("WeatherAPI", "API call failed with response code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Replace {lat}, {lon}, and {lang} with actual values
        String lat = "39.8746188";
        String lon = "32.7450159";

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
                System.out.println("Latitude: " + weatherData.lat);
                System.out.println("Longitude: " + weatherData.lon);
                System.out.println("Timezone: " + weatherData.timezone);

                WeatherData.CurrentWeather currentWeather = weatherData.current;
                System.out.println("Temperature: " + currentWeather.temp);
                System.out.println("Feels Like: " + currentWeather.feels_like);

                WeatherData.HourlyWeather[] hourlyWeather = weatherData.hourly;
                System.out.println("Hourly Weather:");
                for (WeatherData.HourlyWeather hour : hourlyWeather) {
                    System.out.println("Time: " + hour.dt);
                    System.out.println("Temperature: " + hour.temp);
                    System.out.println("Feels Like: " + hour.feels_like);
                    System.out.println("Description: " + hour.weather[0].description);
                    System.out.println("Icon: " + hour.weather[0].icon);
                    System.out.println();
                }
            } else {
                System.out.println("API call failed with response code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
