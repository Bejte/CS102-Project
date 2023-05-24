package com.example.catastrophecompass.DataLayer.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 *  Same class denoted as Logistics in doc
 */
@Entity
public class LogisticInfo {

    @PrimaryKey
    private int id = 1;
    private String getName, getAddress, dropName, dropAddress, status, pictureUrl;
    private boolean getStatus, dropStatus;
    private InventoryList inventoryList;

    public LogisticInfo(String getName, String getAddress, String dropName, String dropAddress, String status, String pictureUrl, boolean getStatus, boolean dropStatus, InventoryList inventoryList) {
        this.getName = getName;
        this.getAddress = getAddress;
        this.dropName = dropName;
        this.dropAddress = dropAddress;
        this.status = status;
        this.pictureUrl = pictureUrl;
        this.getStatus = getStatus;
        this.dropStatus = dropStatus;
        this.inventoryList = inventoryList;
    }

    public LogisticInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGetName() {
        return getName;
    }

    public void setGetName(String getName) {
        this.getName = getName;
    }

    public String getGetAddress() {
        return getAddress;
    }

    public void setGetAddress(String getAddress) {
        this.getAddress = getAddress;
    }

    public String getDropName() {
        return dropName;
    }

    public void setDropName(String dropName) {
        this.dropName = dropName;
    }

    public String getDropAddress() {
        return dropAddress;
    }

    public void setDropAddress(String dropAddress) {
        this.dropAddress = dropAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public boolean isGetStatus() {
        return getStatus;
    }

    public void setGetStatus(boolean getStatus) {
        this.getStatus = getStatus;
    }

    public boolean isDropStatus() {
        return dropStatus;
    }

    public void setDropStatus(boolean dropStatus) {
        this.dropStatus = dropStatus;
    }

    public InventoryList getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(InventoryList inventoryList) {
        this.inventoryList = inventoryList;
    }
}
