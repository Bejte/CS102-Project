package com.example.catastrophecompass.UILayer.Common;

public class AidItem {
    private String name, address, mostUrgentItem;

    public AidItem(String name, String address, String mostUrgentItem) {
        this.name = name;
        this.address = address;
        this.mostUrgentItem = mostUrgentItem;
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

    public String getMostUrgentItem() {
        return mostUrgentItem;
    }

    public void setMostUrgentItem(String mostUrgentItem) {
        this.mostUrgentItem = mostUrgentItem;
    }
}
