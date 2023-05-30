package com.example.catastrophecompass.UILayer.HQOrganizer;

import com.example.catastrophecompass.DataLayer.Model.DriverItem;

import java.util.List;

public interface OrganizeTrucksInterface {
    void setDisplay (List<DriverItem> list);
     void warnUser();
}
