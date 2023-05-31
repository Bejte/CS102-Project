package com.example.catastrophecompass.UILayer.HQOrganizer;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.PlacesAvailableFBRepo;
import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;
import com.example.catastrophecompass.DataLayer.Model.Request;

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
        Log.d("PlacesAvailableVM", "getPlacesAvailable() called");
        placesAvailableFBRepo.getPlacesAvailable(logisticInfo,placesAvailableInterface);
    }
    public void assign(Logistics logistics, Request request, PlacesAvailableInterface placesAvailableInterface){ //TODO Logistics to LogisticInfo??
        Log.d("PlacesAvailableVM", "assign() called");
        placesAvailableFBRepo.assaign(logistics,request,placesAvailableInterface);
    }



}
