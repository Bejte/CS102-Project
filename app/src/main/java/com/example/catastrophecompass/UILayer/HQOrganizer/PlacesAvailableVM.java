package com.example.catastrophecompass.UILayer.HQOrganizer;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PlacesAvailableVM extends ViewModel {

    private PlacesAvailableFBRepo placesAvailableFBRepo;



    @Inject
    public PlacesAvailableVM(PlacesAvailableFBRepo placesAvailableFBRepo) {
        this.placesAvailableFBRepo = placesAvailableFBRepo;
    }
    public void getPlacesAvailable (LogisticInfo logisticInfo, PlacesAvailableInterface placesAvailableInterface){
        placesAvailableFBRepo.getPlacesAvailable(logisticInfo,placesAvailableInterface);
    }
    public void assaign(Logistics logistics, Request request,PlacesAvailableInterface placesAvailableInterface){
        placesAvailableFBRepo.assaign(logistics,request,placesAvailableInterface);
    }



}
