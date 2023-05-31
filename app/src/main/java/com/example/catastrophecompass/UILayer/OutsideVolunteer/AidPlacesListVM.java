package com.example.catastrophecompass.UILayer.OutsideVolunteer;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.AidPlacesListFBRepo;
import com.example.catastrophecompass.DataLayer.Model.AItem;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AidPlacesListVM extends ViewModel {

    private AidPlacesListFBRepo FBRepo;

    @Inject
    public AidPlacesListVM(AidPlacesListFBRepo FBRepo) {
        this.FBRepo = FBRepo;
    }
    public List<AItem> getAidPlacesList (String cityName){
        Log.d("AidPlacesListVM", "getAidPlacesList() called");
        return FBRepo.getAItemList(cityName);
    }
}
