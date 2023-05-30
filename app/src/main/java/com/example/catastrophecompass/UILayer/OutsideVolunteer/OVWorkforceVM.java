package com.example.catastrophecompass.UILayer.OutsideVolunteer;

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
import com.example.catastrophecompass.DataLayer.RemoteDataRepository.WeatherRepository.WeatherAPI;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.Disposable;
@HiltViewModel
public class OVWorkforceVM extends ViewModel {

    private WeatherAPI weatherAPI;

    @Inject
    public OVWorkforceVM(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
    }

    public WorkplaceWeather getWeatherInfo(String location){
        Log.d("OVWorkforceVM", "getWeatherInfo() called");
        return weatherAPI.getWeatherInfo(location);
    }
}