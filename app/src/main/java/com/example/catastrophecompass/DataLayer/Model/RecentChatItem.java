package com.example.catastrophecompass.DataLayer.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RecentChatItem {

    private String time; // parse to string when getting time

    @PrimaryKey
    private String chattedUserName;
    private String lastMessage, pictureUrl;

    public RecentChatItem(String time, String chattedUserName, String lastMessage, String pictureUrl) {
        this.time = time;
        this.chattedUserName = chattedUserName;
        this.lastMessage = lastMessage;
        this.pictureUrl = pictureUrl;
    }

    public RecentChatItem() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getChattedUserName() {
        return chattedUserName;
    }

    public void setChattedUserName(String chattedUserName) {
        this.chattedUserName = chattedUserName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
