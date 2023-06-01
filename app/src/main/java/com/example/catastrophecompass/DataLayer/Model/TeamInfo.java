package com.example.catastrophecompass.DataLayer.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class TeamInfo {

    @NonNull
    @PrimaryKey
    private String teamName;
    private String teamDescription, TeamLeaderName, areaInfo, foodInfo, location, url;
    @Ignore
    private VolunteerInfo volunteerInfo;

    public TeamInfo(String teamName, String teamDescription, String teamLeaderName, String areaInfo, String foodInfo, String location, VolunteerInfo volunteerInfo, String url) {
        this.teamName = teamName;
        this.teamDescription = teamDescription;
        TeamLeaderName = teamLeaderName;
        this.areaInfo = areaInfo;
        this.foodInfo = foodInfo;
        this.location = location;
        this.volunteerInfo = volunteerInfo;
        this.url = url;
    }

    public TeamInfo() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }

    public String getTeamLeaderName() {
        return TeamLeaderName;
    }

    public void setTeamLeaderName(String teamLeaderName) {
        TeamLeaderName = teamLeaderName;
    }

    public String getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    public String getFoodInfo() {
        return foodInfo;
    }

    public void setFoodInfo(String foodInfo) {
        this.foodInfo = foodInfo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public VolunteerInfo getVolunteerInfo() {
        return volunteerInfo;
    }

    public void setVolunteerInfo(VolunteerInfo volunteerInfo) {
        this.volunteerInfo = volunteerInfo;
    }

    public String getUrl(){
        return this.url;
    }

    public void setUrl(String url){
        this.url = url;
    }
}
