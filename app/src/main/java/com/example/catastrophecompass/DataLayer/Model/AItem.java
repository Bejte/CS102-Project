package com.example.catastrophecompass.DataLayer.Model;

public class AItem {

    private String name, address, mostUrgent;

    public AItem(String name, String address, String mostUrgent) {
        this.name = name;
        this.address = address;
        this.mostUrgent = mostUrgent;
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

    public String getMostUrgent() {
        return mostUrgent;
    }

    public void setMostUrgent(String mostUrgent) {
        this.mostUrgent = mostUrgent;
    }
}
