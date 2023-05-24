package com.example.catastrophecompass.DataLayer.Model;

public class TruckItem {

    private String driverName;
    private int truckSize; // 1:small, 2:medium, 4:large

    public TruckItem(String driverName, int truckSize) {
        this.driverName = driverName;
        this.truckSize = truckSize;
    }

    public TruckItem() {
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getTruckSize() {
        return truckSize;
    }

    public void setTruckSize(int truckSize) {
        this.truckSize = truckSize;
    }
}
