package com.example.catastrophecompass.UILayer.FieldOrganizer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.DemographicInfo;
import com.example.catastrophecompass.DataLayer.Model.HousingInfo;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<DemographicInfo> demographicInfo = new MutableLiveData<>();
    private MutableLiveData<HousingInfo> housingInfo = new MutableLiveData<>();

    public void preDisplay(DemographicInfo demoInfo, HousingInfo houseInfo) {
        if(demoInfo != null) {
            demographicInfo.setValue(demoInfo);
        }
        if(houseInfo != null) {
            housingInfo.setValue(houseInfo);
        }
    }

    public LiveData<DemographicInfo> getDemographicInfo() {
        return demographicInfo;
    }

    public LiveData<HousingInfo> getHousingInfo() {
        return housingInfo;
    }
}