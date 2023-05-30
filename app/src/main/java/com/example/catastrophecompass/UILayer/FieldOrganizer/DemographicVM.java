package com.example.catastrophecompass.UILayer.FieldOrganizer;
import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.User;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DemographicVM extends ViewModel implements demographicInterface {

    private FieldOrganizationInfoDomain fo_info_do;

    @Inject
    public DemographicVM(FieldOrganizationInfoDomain fo_info_do) {
        this.fo_info_do = fo_info_do;
    }

    public void setupDatabaseConnection(User user) {
        fo_info_do.setupDatabaseConnection(user);
    }

    @Override
    public void getDemographicInfo(demographicInterface demoint) {
        fo_info_do.getDemographicInfo(this);
    }

    public void getHousingInfo(housingInterface houseint) {
        fo_info_do.getHousingInfo(houseint);
    }
}
