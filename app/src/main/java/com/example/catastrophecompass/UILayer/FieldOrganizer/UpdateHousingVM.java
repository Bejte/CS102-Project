package com.example.catastrophecompass.UILayer.FieldOrganizer;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.FieldOrganizatonInfoFBRepo;
import com.example.catastrophecompass.DataLayer.Model.DemographicInfo;
import com.example.catastrophecompass.DataLayer.Model.HousingInfo;
import com.example.catastrophecompass.DomainLayer.Common.FieldOrganizerCommon;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UpdateHousingVM extends ViewModel {

    private FieldOrganizatonInfoFBRepo FBRepo;

    @Inject
    public UpdateHousingVM(FieldOrganizatonInfoFBRepo FBRepo) {
        this.FBRepo = FBRepo;
    }

    public boolean updateHousingInfo(HousingInfo housingInfo) {
        Log.d("UpdateHousingVM", "updateHousingInfo() called");
        return FBRepo.updateHousingInfo(housingInfo, FieldOrganizerCommon.organizationName);
    }

}