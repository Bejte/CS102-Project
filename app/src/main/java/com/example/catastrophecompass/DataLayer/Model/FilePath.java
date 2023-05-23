package com.example.catastrophecompass.DataLayer.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FilePath {

    @PrimaryKey
    private String filePath;

    public FilePath(String filePath) {
        this.filePath = filePath;
    }

    public FilePath() {
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }




}
