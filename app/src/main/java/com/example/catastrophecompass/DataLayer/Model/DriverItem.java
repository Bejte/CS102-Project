package com.example.catastrophecompass.DataLayer.Model;

public class DriverItem {

    private String driverName, driverStatus,driverSize;

    public DriverItem(String driverName, String driverStatus,String driverSize) {
        this.driverName = driverName;
        this.driverStatus = driverStatus;
        this.driverSize=driverSize;
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

    public String getDriverSize() {
        return driverSize;
    }
}
