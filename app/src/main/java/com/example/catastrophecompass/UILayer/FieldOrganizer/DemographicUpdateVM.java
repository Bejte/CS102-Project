package com.example.catastrophecompass.UILayer.FieldOrganizer;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.FieldOrganizatonInfoFBRepo;
import com.example.catastrophecompass.DataLayer.Model.DemographicInfo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DemographicUpdateVM extends ViewModel {

    private FieldOrganizatonInfoFBRepo FBRepo;

    @Inject
    public DemographicUpdateVM(FieldOrganizatonInfoFBRepo FBRepo) {
        this.FBRepo = FBRepo;
    }

    public boolean updateDemographicInfo(DemographicInfo demoInfo) {
        Log.d("DemographicUpdateVM", "updateDemographicInfo() called");
        return FBRepo.updateDemographicInfo(demoInfo, FieldOrganizationCommon.organizationName);
    }
}
