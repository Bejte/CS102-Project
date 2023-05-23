package com.example.catastrophecompass.DataLayer.Model;

import androidx.room.Entity;

@Entity
public class VIBJobInfo {

    private int id; // use the same id everytime to delete VIBDao
    private String teamName, teamDescription, teamLeaderName, areaInfo, foodInfo, location, teamLeaderPicUrl;
    private boolean dispatch;

    public VIBJobInfo(String teamName, String teamDescription, String teamLeaderName, String areaInfo, String foodInfo, String location, String teamLeaderPicUrl, boolean dispatch) {
        this.teamName = teamName;
        this.teamDescription = teamDescription;
        this.teamLeaderName = teamLeaderName;
        this.areaInfo = areaInfo;
        this.foodInfo = foodInfo;
        this.location = location;
        this.teamLeaderPicUrl = teamLeaderPicUrl;
        this.dispatch = dispatch;
    }

    public VIBJobInfo() {
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
        return teamLeaderName;
    }

    public void setTeamLeaderName(String teamLeaderName) {
        this.teamLeaderName = teamLeaderName;
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

    public String getTeamLeaderPicUrl() {
        return teamLeaderPicUrl;
    }

    public void setTeamLeaderPicUrl(String teamLeaderPicUrl) {
        this.teamLeaderPicUrl = teamLeaderPicUrl;
    }

    public boolean isDispatch() {
        return dispatch;
    }

    public void setDispatch(boolean dispatch) {
        this.dispatch = dispatch;
    }
}
