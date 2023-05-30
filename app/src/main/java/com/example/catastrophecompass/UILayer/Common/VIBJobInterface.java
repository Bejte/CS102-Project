package com.example.catastrophecompass.UILayer.Common;

import com.example.catastrophecompass.DataLayer.Model.VIBJobInfo;

public interface VIBJobInterface {
    void setDisplay(VIBJobInfo jobInfo);
    void warnUser();
    void displayTLPic(String picturePath);
    void warnUserNoPicture();
}
