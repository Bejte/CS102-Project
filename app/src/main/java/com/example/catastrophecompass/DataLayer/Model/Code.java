package com.example.catastrophecompass.DataLayer.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Code {


    @PrimaryKey(autoGenerate = true)
    private int id; // needed for database
    private String code;
    private String placeName;

    public Code(String code, String placeName) {
        this.code = code;
        this.placeName = placeName;
    }

    public Code() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setId(int id) {
        this.id = id;
    }
}
