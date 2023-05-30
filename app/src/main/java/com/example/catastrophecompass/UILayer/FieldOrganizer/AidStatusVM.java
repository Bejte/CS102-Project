package com.example.catastrophecompass.UILayer.FieldOrganizer;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AidStatusVM extends ViewModel implements demographicInterface {

    private FieldOrganizationInfoDomain fo_info_do;

    @Inject
    public AidStatusVM(FieldOrganizationInfoDomain fo_info_do) {
        this.fo_info_do = fo_info_do;
    }



    @Override
    public void getInventoryInfo(InventoryInterface demoint) {
        fo_info_do.getInventoryInfo(demoint);
    }

    public void getArrivingInfo(InventoryInterface houseint) {
        fo_info_do.getArrivingInfo(houseint);
    }
}