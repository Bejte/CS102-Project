package com.example.catastrophecompass.UILayer.OutsideVolunteer;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.AidPlaceFBRepo;
import com.example.catastrophecompass.DataLayer.Model.InventoryList;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AidActivityVM extends ViewModel {

    private AidPlaceFBRepo FBRepo;
    @Inject
    public AidActivityVM(AidPlaceFBRepo FBRepo) {
        this.FBRepo = FBRepo;
    }
    public List<InventoryList> getItemList(String place){
        return FBRepo.getItemList(place);
        // TODO convert Item to InventoryList
    }
}