package com.example.catastrophecompass.DataLayer.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class VolunteerInfo {

    @PrimaryKey
    private int id;

    private int need, crucial, helpful, unnecessary;

    public VolunteerInfo(int id, int need, int crucial, int helpful, int unnecessary) {
        this.id = id;
        this.need = need;
        this.crucial = crucial;
        this.helpful = helpful;
        this.unnecessary = unnecessary;
    }

    public VolunteerInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNeed() {
        return need;
    }

    public void setNeed(int need) {
        this.need = need;
    }

    public int getCrucial() {
        return crucial;
    }

    public void setCrucial(int crucial) {
        this.crucial = crucial;
    }

    public int getHelpful() {
        return helpful;
    }

    public void setHelpful(int helpful) {
        this.helpful = helpful;
    }

    public int getUnnecessary() {
        return unnecessary;
    }

    public void setUnnecessary(int unnecessary) {
        this.unnecessary = unnecessary;
    }
}
