package com.example.catastrophecompass.DataLayer.Model;

public class OrganizationAsNode {

    private String name, type;

    public OrganizationAsNode(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public OrganizationAsNode() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
