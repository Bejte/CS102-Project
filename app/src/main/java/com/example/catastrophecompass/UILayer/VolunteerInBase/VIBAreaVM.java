package com.example.catastrophecompass.UILayer.VolunteerInBase;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.WorkplaceWeather;
import com.example.catastrophecompass.DomainLayer.Domain.VIBAreaDomain;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class VIBAreaVM extends ViewModel {


    private VIBAreaDomain vibAreaDomain;

    @Inject
    public VIBAreaVM(VIBAreaDomain vibAreaDomain) {
        this.vibAreaDomain = vibAreaDomain;
    }
    public void updateFoodInfo (String food){
        Log.d("VIBAreaVM", "updateFoodInfo() called");
       vibAreaDomain.updateFoodInfo(food);
    }
    public WorkplaceWeather getAreaInfo(VIBAreaInterface VIBinterface) {
        Log.d("VIBAreaVM", "getAreaInfo() called");
        return vibAreaDomain.getAreaInfo(VIBinterface);
    }
}