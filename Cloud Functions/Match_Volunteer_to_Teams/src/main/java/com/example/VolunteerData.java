package com.example;

public class VolunteerData {
    private String teamName;
    private VolunteerInfo volunteerInfo;
    private String areaInfo;
    private String jobInfo;
    private String teamLeaderName;
    private String teamLeaderProfilePicURL;
    private String foodInfo;
    private int urgency;

    public VolunteerData(){};

    public VolunteerData(VolunteerInfo volunteerInfo, String areaInfo, String jobInfo, String teamLeaderName,
                            String teamLeaderProfilePicURL, String foodInfo, int urgency) {
        this.volunteerInfo = volunteerInfo;
        this.areaInfo = areaInfo;
        this.jobInfo = jobInfo;
        this.teamLeaderName = teamLeaderName;
        this.teamLeaderProfilePicURL = teamLeaderProfilePicURL;
        this.foodInfo = foodInfo;
        this.urgency = urgency;
    }

    // Getters and setters (omitted for brevity)


    public VolunteerInfo getVolunteerInfo() {
        return this.volunteerInfo;
    }

    public void setVolunteerInfo(VolunteerInfo volunteerInfo) {
        this.volunteerInfo = volunteerInfo;
    }

    public String getAreaInfo() {
        return this.areaInfo;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public VolunteerData setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public String getJobInfo() {
        return this.jobInfo;
    }

    public void setJobInfo(String jobInfo) {
        this.jobInfo = jobInfo;
    }

    public String getTeamLeaderName() {
        return this.teamLeaderName;
    }

    public void setTeamLeaderName(String teamLeaderName) {
        this.teamLeaderName = teamLeaderName;
    }

    public String getTeamLeaderProfilePicURL() {
        return this.teamLeaderProfilePicURL;
    }

    public void setTeamLeaderProfilePicURL(String teamLeaderProfilePicURL) {
        this.teamLeaderProfilePicURL = teamLeaderProfilePicURL;
    }

    public String getFoodInfo() {
        return this.foodInfo;
    }

    public void setFoodInfo(String foodInfo) {
        this.foodInfo = foodInfo;
    }

    public int getUrgency() {
        return this.urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }

}
