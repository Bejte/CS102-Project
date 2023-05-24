package com.example.catastrophecompass.DataLayer.Model;

public class TruckRequest {

    private InventoryList inventoryList;
    private int requestSize;

    public TruckRequest(InventoryList inventoryList, int requestSize) {
        this.inventoryList = inventoryList;
        this.requestSize = requestSize;
    }

    public TruckRequest() {
    }

    public InventoryList getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(InventoryList inventoryList) {
        this.inventoryList = inventoryList;
    }

    public int getRequestSize() {
        return requestSize;
    }

    public void setRequestSize(int requestSize) {
        this.requestSize = requestSize;
    }
}
