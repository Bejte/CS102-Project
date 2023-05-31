package com.example.catastrophecompass.DataLayer.Model;

public class HQO extends Member {

    private String name, password, picture, pictureUrl;
    private boolean isAdmin;

    public HQO(String name, String password, String picture, String pictureUrl, boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.picture = picture;
        this.pictureUrl = pictureUrl;
        this.isAdmin = isAdmin;
    }

    public HQO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
