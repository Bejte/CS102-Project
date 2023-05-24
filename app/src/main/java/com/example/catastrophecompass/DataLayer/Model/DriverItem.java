package com.example.catastrophecompass.DataLayer.Model;

public class DriverItem {

    private String driverName, driverStatus;

    public DriverItem(String driverName, String driverStatus) {
        this.driverName = driverName;
        this.driverStatus = driverStatus;
    }

    public DriverItem() {
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(String driverStatus) {
        this.driverStatus = driverStatus;
    }
}
