package com.example.catastrophecompass.DataLayer.Model;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Decide if you want to hold picture as Bitmap or Uri
 * delete the other one!
 */
public class Team {

    private String teamName, description, teamLeader, teamLeaderPassword;
    private Uri teamLeaderProfilePictureAsUri;
    private Bitmap teamLeaderProfilePictureAsBitmap;

    public Team() {
    }

    public Team(String teamName, String description, String teamLeader, String teamLeaderPassword, Uri teamLeaderProfilePictureAsUri, Bitmap teamLeaderProfilePictureAsBitmap) {
        this.teamName = teamName;
        this.description = description;
        this.teamLeader = teamLeader;
        this.teamLeaderPassword = teamLeaderPassword;
        this.teamLeaderProfilePictureAsUri = teamLeaderProfilePictureAsUri;
        this.teamLeaderProfilePictureAsBitmap = teamLeaderProfilePictureAsBitmap;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public String getTeamLeaderPassword() {
        return teamLeaderPassword;
    }

    public void setTeamLeaderPassword(String teamLeaderPassword) {
        this.teamLeaderPassword = teamLeaderPassword;
    }

    public Uri getTeamLeaderProfilePictureAsUri() {
        return teamLeaderProfilePictureAsUri;
    }

    public void setTeamLeaderProfilePictureAsUri(Uri teamLeaderProfilePictureAsUri) {
        this.teamLeaderProfilePictureAsUri = teamLeaderProfilePictureAsUri;
    }

    public Bitmap getTeamLeaderProfilePictureAsBitmap() {
        return teamLeaderProfilePictureAsBitmap;
    }

    public void setTeamLeaderProfilePictureAsBitmap(Bitmap teamLeaderProfilePictureAsBitmap) {
        this.teamLeaderProfilePictureAsBitmap = teamLeaderProfilePictureAsBitmap;
    }
}