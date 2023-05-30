package com.example.catastrophecompass.UILayer.HQOrganizer;

import com.example.catastrophecompass.DataLayer.Model.DriverItem;
import com.example.catastrophecompass.DataLayer.Model.Request;

import java.util.List;

public interface PlacesAvailableInterface {
    void setDisplay (List<Request> list);
    void warnUserNoConnection();
    void notifySuccessfulAssignment();
    void notifyFailedAssignment();
}
