package com.example;

public class NeedTeam {
    private String teamName;
    private String city;
    private String place;
    private int need;

    public NeedTeam(){};


    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getNeed() {
        return this.need;
    }

    public void setNeed(int need) {
        this.need = need;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public NeedTeam setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }
    

}
