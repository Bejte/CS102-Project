package com.example.catastrophecompass.UILayer.TeamOrganizor;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.ArrivingTrucksFBRepo;
import com.example.catastrophecompass.DataLayer.Model.TruckItem;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ArrivingTrucksVM extends ViewModel {
    private ArrivingTrucksFBRepo FBRepo;

    @Inject
    public ArrivingTrucksVM(ArrivingTrucksFBRepo FBRepo){
        this.FBRepo = FBRepo;
    }

    public List<TruckItem> getArrivingTrucks(String organizationName){
        Log.d("ArrivingTrucksVM", "getArrivingTrucks() called");
        return FBRepo.getArrivingTrucks(organizationName);
    }
}
