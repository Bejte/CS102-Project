package com.example.catastrophecompass.UILayer.HQOrganizer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import static androidx.fragment.app.FragmentManager.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.room.Insert;

import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;
import com.example.catastrophecompass.DataLayer.Model.WItem;
import com.example.catastrophecompass.DomainLayer.Domain.VIBAreaDomain;
import com.example.catastrophecompass.UILayer.Common.VIBAreaInterface;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.Disposable;
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
