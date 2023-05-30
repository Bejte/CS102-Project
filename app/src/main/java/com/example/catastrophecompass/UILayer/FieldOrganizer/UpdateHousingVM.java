package com.example.catastrophecompass.UILayer.FieldOrganizer;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.FieldOrganizatonInfoFBRepo;
import com.example.catastrophecompass.DataLayer.Model.DemographicInfo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UpdateHousingVM extends ViewModel {

    private FieldOrganizatonInfoFBRepo FBRepo;

    @Inject
    public UpdateHousingVM(FieldOrganizatonInfoFBRepo FBRepo) {
        this.FBRepo = FBRepo;
    }

    public boolean updateHousingInfo(DemographicInfo demoInfo) {
        Log.d("UpdateHousingVM", "updateHousingInfo() called");
        return FBRepo.updateHousingInfo(demoInfo, FieldOrganizerCommon.organizationName);
    }

}