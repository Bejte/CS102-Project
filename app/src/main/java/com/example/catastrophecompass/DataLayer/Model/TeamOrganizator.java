package com.example.catastrophecompass.DataLayer.Model;

import android.graphics.Bitmap;
import android.net.Uri;

public class TeamOrganizator extends Member {

    private String name, password, pictureUrl;
    private Uri pictureAsUri;
    private Bitmap pictureAsBitmap;

    public TeamOrganizator(String name, String password, String pictureUrl, Uri pictureAsUri) {
        super();
        this.name = name;
        this.password = password;
        this.pictureUrl = pictureUrl;
        this.pictureAsUri = pictureAsUri;
    }





    public TeamOrganizator(String name, String password, String pictureUrl, Bitmap pictureAsBitmap) {
        super();
        this.name = name;
        this.password = password;
        this.pictureUrl = pictureUrl;
        this.pictureAsBitmap = pictureAsBitmap;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Uri getPictureAsUri() {
        return pictureAsUri;
    }

    public void setPictureAsUri(Uri pictureAsUri) {
        this.pictureAsUri = pictureAsUri;
    }

    public Bitmap getPictureAsBitmap() {
        return pictureAsBitmap;
    }

    public void setPictureAsBitmap(Bitmap pictureAsBitmap) {
        this.pictureAsBitmap = pictureAsBitmap;
    }

    public TeamOrganizator() {
        super();
    }
}
