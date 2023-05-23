package com.example.catastrophecompass.DataLayer.Model;

public class WItemFB {

    private String name, workUrgency, address, location;

    public WItemFB(String name, String workUrgency, String address, String location) {
        this.name = name;
        this.workUrgency = workUrgency;
        this.address = address;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkUrgency() {
        return workUrgency;
    }

    public void setWorkUrgency(String workUrgency) {
        this.workUrgency = workUrgency;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
