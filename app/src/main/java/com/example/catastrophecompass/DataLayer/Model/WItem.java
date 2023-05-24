package com.example.catastrophecompass.DataLayer.Model;

public class WItem {

    private String name, workUrgency, address, currentTemp, currentWeatherType, location;

    public WItem(String name, String workUrgency, String address, String currentTemp, String currentWeatherType, String location) {
        this.name = name;
        this.workUrgency = workUrgency;
        this.address = address;
        this.currentTemp = currentTemp;
        this.currentWeatherType = currentWeatherType;
        this.location = location;
    }

    public WItem(WItemFB wItemFB, WItemWeather wItemWeather){
        this.name = wItemFB.getName();
        this.workUrgency = wItemFB.getWorkUrgency();
        this.address = wItemFB.getAddress();
        this.location = wItemFB.getLocation();
        this.currentTemp = wItemWeather.getCurrentTemp();
        this.currentWeatherType = wItemWeather.getCurrentWeatherType();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkUrgency() {
        return workUrgency;
    }

    public void setWorkUrgency(String workUrgency) {
        this.workUrgency = workUrgency;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
