package com.example.catastrophecompass.DataLayer.Model;

public class Contact {

    private String contactName, pictureUrl;

    public Contact(String contactName, String pictureUrl) {
        this.contactName = contactName;
        this.pictureUrl = pictureUrl;
    }

    public Contact() {
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
