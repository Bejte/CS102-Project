package com.example;

public class VolunteerInfo {
    private int crucial;
    private int helpful;
    private int unnecessary;
    private int need;

    public VolunteerInfo(){};

    public VolunteerInfo(int crucial, int helpful, int unnecessary, int need) {
        this.crucial = crucial;
        this.helpful = helpful;
        this.unnecessary = unnecessary;
        this.need = need;
    }

    // Getters and setters (omitted for brevity)

    public int getCrucial() {
        return this.crucial;
    }

    public void setCrucial(int crucial) {
        this.crucial = crucial;
    }

    public int getHelpful() {
        return this.helpful;
    }

    public void setHelpful(int helpful) {
        this.helpful = helpful;
    }

    public int getUnnecessary() {
        return this.unnecessary;
    }

    public void setUnnecessary(int unnecessary) {
        this.unnecessary = unnecessary;
    }

    public int getNeed() {
        return this.need;
    }

    public void setNeed(int need) {
        this.need = need;
    }
}
