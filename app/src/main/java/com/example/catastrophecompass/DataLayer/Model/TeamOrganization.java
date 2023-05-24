package com.example.catastrophecompass.DataLayer.Model;

public class TeamOrganization extends OrganizationAsNode{

    private String areaCode, city;

    public TeamOrganization(String name, String type, String areaCode, String city) {
        super(name, type);
        this.areaCode = areaCode;
        this.city = city;
    }

    public TeamOrganization() {
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
