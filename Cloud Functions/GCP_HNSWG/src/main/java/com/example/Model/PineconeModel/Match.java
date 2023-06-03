package com.example.Model.PineconeModel;

import java.util.List;

public class Match {

    private String id;
    private double score;
    private List<Float> values;

    public Match(String id, double score, List<Float> values) {
        this.id = id;
        this.score = score;
        this.values = values;
    }

    public Match() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<Float> getValues() {
        return values;
    }

    public void setValues(List<Float> values) {
        this.values = values;
    }
}
