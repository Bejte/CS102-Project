package com.example.catastrophecompass.UILayer.TeamOrganizor;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.RecordAidRequestTruckFBRepo;
import com.example.catastrophecompass.DataLayer.Model.TruckRequest;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RecordAidRequestTruckVM extends ViewModel {
    private RecordAidRequestTruckFBRepo FBRepo;

    @Inject
    public RecordAidRequestTruckVM(RecordAidRequestTruckFBRepo FBRepo) {
        this.FBRepo = FBRepo;
    }

    public boolean makeRequest(String organizationName, TruckRequest request){
        Log.d("RecordAidRequestTruckVM", "makeRequest() called");
        return FBRepo.makeRequest(organizationName, request);
    }
}
