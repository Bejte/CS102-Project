package com.example.catastrophecompass.UILayer.Common;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import static androidx.fragment.app.FragmentManager.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.room.Insert;

import com.example.catastrophecompass.DataLayer.Model.WorkplaceWeather;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.Disposable;
@HiltViewModel
public class OVWorkforceVM extends ViewModel {

    private UC uc;
    private REPO FBRepo;
    private WorkplaceWeather wp;

    @Inject
    public OVWorkforceVM(UC uc) {
        this.uc = uc;
    }
    public WorkplaceWeather getWeatherInfo(String location){
        return FBRepo.getWeatherInfo(location);
    }
}