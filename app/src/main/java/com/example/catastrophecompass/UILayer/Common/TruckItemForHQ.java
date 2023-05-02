package com.example.catastrophecompass.UILayer.Common;


public class TruckItemForHQ {
    private String driverName;
    private String size;
    private String status;

    public TruckItemForHQ(String driverName, String size, String status) {
        this.driverName = driverName;
        this.size = size;
        this.status = status;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getSize() {
        return size;
    }

    public String getStatus() {
        return status;
    }
}
