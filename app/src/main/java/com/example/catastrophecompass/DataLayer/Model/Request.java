package com.example.catastrophecompass.DataLayer.Model;

public class Request {

    private String requestName, placeName;
    private int size;
    private InventoryList inventoryList;

    public Request(String requestName, String placeName, int size, InventoryList inventoryList) {
        this.requestName = requestName;
        this.placeName = placeName;
        this.size = size;
        this.inventoryList = inventoryList;
    }

    public Request() {
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public InventoryList getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(InventoryList inventoryList) {
        this.inventoryList = inventoryList;
    }
}
