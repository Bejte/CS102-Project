package com.example.catastrophecompass.DataLayer.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Credentials {

    @PrimaryKey
    private int id;
    private String city, place, teamName;

    public Credentials(int id, String city, String place, String teamName) {
        this.id = id;
        this.city = city;
        this.place = place;
        this.teamName = teamName;
    }


    public Credentials() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

}
