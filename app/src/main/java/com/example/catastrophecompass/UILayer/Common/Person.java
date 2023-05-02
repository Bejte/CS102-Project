package com.example.catastrophecompass.UILayer.Common;

public class Person {
    private String name;
    private int profileImage;

    public Person(String name, int profileImage) {
        this.name = name;
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public int getProfileImage() {
        return profileImage;
    }
}
