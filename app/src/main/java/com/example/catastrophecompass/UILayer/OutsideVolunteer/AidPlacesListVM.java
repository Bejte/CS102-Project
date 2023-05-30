package com.example.catastrophecompass.UILayer.OutsideVolunteer;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.AidPlacesListFBRepo;
import com.example.catastrophecompass.UILayer.Common.AidItem;

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
    public List<AidItem> getAidPlacesList (String cityName){
        return FBRepo.getAidPlacesList(cityName);
    }
}
