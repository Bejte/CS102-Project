package com.example.catastrophecompass.DataLayer.Model;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class ChatItem {

    @PrimaryKey
    private int time;

    private String message, pictureUrl, sender;

    @Ignore
    private Uri pictureAsUri;

    @Ignore
    private Bitmap pictureAsBitmap;

    private boolean isPicture;

    public ChatItem(int time, String message, String pictureUrl, String sender, Uri pictureAsUri, boolean isPicture) {
        this.time = time;
        this.message = message;
        this.pictureUrl = pictureUrl;
        this.sender = sender;
        this.pictureAsUri = pictureAsUri;
    }

    public ChatItem(int time, String message, String pictureUrl, String sender, Bitmap pictureAsBitmap, boolean isPicture) {
        this.time = time;
        this.message = message;
        this.pictureUrl = pictureUrl;
        this.sender = sender;
        this.pictureAsBitmap = pictureAsBitmap;
        this.isPicture = isPicture;
    }

    public ChatItem() {
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
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

    public boolean isPicture() {
        return isPicture;
    }

    public void setPicture(boolean picture) {
        isPicture = picture;
    }
}
