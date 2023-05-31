package com.example.catastrophecompass.DataLayer.Model;

public class Job {
    private String teamName;
    private int urgency;

    public Job(String teamName, int urgency) {
        this.teamName = teamName;
        this.urgency = urgency;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }
}
