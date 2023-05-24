package com.example.catastrophecompass.DataLayer.Model;

import android.graphics.Bitmap;
import android.net.Uri;

public class TeamOrganizator {

    private String name, password, pictureUrl;
    private Uri pictureAsUri;
    private Bitmap pictureAsBitmap;

    public TeamOrganizator(String name, String password, String pictureUrl, Uri pictureAsUri) {
        this.name = name;
        this.password = password;
        this.pictureUrl = pictureUrl;
        this.pictureAsUri = pictureAsUri;
    }

    public TeamOrganizator(String name, String password, String pictureUrl, Bitmap pictureAsBitmap) {
        this.name = name;
        this.password = password;
        this.pictureUrl = pictureUrl;
        this.pictureAsBitmap = pictureAsBitmap;
    }

    public TeamOrganizator() {
    }
}
