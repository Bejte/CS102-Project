package com.example.catastrophecompass.UILayer.Common;

public class WorkforceItem {
    private String name;
    private String address;
    private String degree;
    private String weatherType;

    public WorkforceItem(String name, String address, String degree, String weatherType) {
        this.name = name;
        this.address = address;
        this.degree = degree;
        this.weatherType = weatherType;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}