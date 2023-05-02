package com.example.catastrophecompass.UILayer.Common.Chat;

public class Chat {
    private String sender;
    private String lastMessage;
    private int unreadCount;
    private int profileImage;

    public Chat(String sender, String lastMessage, int unreadCount, int profileImage) {
        this.sender = sender;
        this.lastMessage = lastMessage;
        this.unreadCount = unreadCount;
        this.profileImage = profileImage;
    }

    public String getSender() {
        return sender;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public int getProfileImage() {
        return profileImage;
    }

    // Add getters and setters
}
