package com.example.catastrophecompass.UILayer.HQOrganizer;

import com.example.catastrophecompass.DataLayer.Model.DriverItem;

import java.util.List;

public interface PlacesAvailableInterface {
    void setDisplay (List<Request> list);
    void warnUserNoConnection();
    void notifySuccessfulAssaignment();
    void notifyFailedAssaignment();
}
