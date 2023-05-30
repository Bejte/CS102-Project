package com.example.catastrophecompass.UILayer.Logistics;

import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;

public interface LogisticMissionInterface {
    void display(LogisticInfo logisticInfo);
    void warnUser();
    void notifyGetSuccess();
    void notifyGetFailed();
    void notifyDropSuccess();
    void notifyDropFailed();
}
