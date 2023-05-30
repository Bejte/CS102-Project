package com.example.catastrophecompass.UILayer.FieldOrganizer;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.User;
import com.example.catastrophecompass.DomainLayer.Domain.FieldOrganizationDomain;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DemographicVM extends ViewModel {

    private FieldOrganizationDomain domain;

    @Inject
    public DemographicVM(FieldOrganizationDomain domain) {
        this.domain = domain;
    }

    public void setupDatabaseConnection(User user) {
        Log.d("DemographicVM", "setupDatabaseConnection() called");
        domain.setupDatabaseConnection(user);
    }

    public void getDemographicInfo(DemographicInterface demographicInterface) {
        Log.d("DemographicVM", "getDemographicInfo() called");
        domain.getDemographicInfo(demographicInterface);
    }

    public void getHousingInfo(HousingInterface housingInterface) {
        Log.d("DemographicVM", "getHousingInfo() called");
        domain.getHousingInfo(housingInterface);
    }
}
