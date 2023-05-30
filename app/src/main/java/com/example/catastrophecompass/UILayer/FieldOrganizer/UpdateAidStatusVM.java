package com.example.catastrophecompass.UILayer.FieldOrganizer;
import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.DemographicInfo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UpdateAidStatusVM extends ViewModel implements demographicInterface {

    private FieldOrganizationInfoDomain fo_info_do;

    @Inject
    public UpdateAidStatusVM(FieldOrganizationInfoDomain fo_info_do) {
        this.fo_info_do = fo_info_do;
    }



    @Override
    public void updateAidStatusInfo(DemographicInfo demographicInfo) {
        fo_info_do.updateAidStatusInfo(demographicInfo);
    }


}