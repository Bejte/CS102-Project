package com.example.catastrophecompass.UILayer.FieldOrganizer;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.DemographicInfo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UpdateHousingVM extends ViewModel implements housingInterface {

    private FieldOrganizationInfoFBRepo fo_info_repo;

    @Inject
    public UpdateHousingVM(FieldOrganizationInfoFBRepo fo_info_repo) {
        this.fo_info_repo = fo_info_repo;
    }

    public void updateHousingInfo(DemographicInfo demoInfo) {
        fo_info_repo.updateHousingInfo(demoInfo);
    }

    @Override
    public void getHousingInfo(housingInterface houseint) {
        fo_info_repo.getHousingInfo(this);
    }
}