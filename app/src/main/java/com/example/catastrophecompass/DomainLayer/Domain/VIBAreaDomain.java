package com.example.catastrophecompass.DomainLayer.Domain;

import android.util.Log;

import com.example.catastrophecompass.DataLayer.FBRepository.VIBFBRepo;
import com.example.catastrophecompass.DataLayer.LocalRepository.VIBAreaLocalRepo;
import com.example.catastrophecompass.DataLayer.Model.VIBJobInfo;
import com.example.catastrophecompass.DataLayer.Model.WorkplaceWeather;
import com.example.catastrophecompass.DataLayer.RemoteDataRepository.WeatherRepository.WeatherAPI;
import com.example.catastrophecompass.UILayer.Common.VIBAreaInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class VIBAreaDomain {
    private VIBAreaLocalRepo localRepo;
    private VIBFBRepo FBRepo;
    private WeatherAPI weather;

    public VIBAreaDomain(VIBAreaLocalRepo localRepo, VIBFBRepo FBRepo, WeatherAPI weather) {
        this.localRepo = localRepo;
        this.FBRepo = FBRepo;
        this.weather = weather;
    }

    public WorkplaceWeather getAreaInfo(VIBAreaInterface vibAreaInterface){
        localRepo.getJobInfo()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<VIBJobInfo>() {
                    @Override
                    public void onNext(VIBJobInfo vibAreaInfo) {
                        vibAreaInterface.setDisplay(vibAreaInfo);
                        Log.d("VIBAreaDomain", "getAreaInfo() onNext() called");
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        vibAreaInterface.warnUser();
                        Log.d("VIBAreaDomain", "getAreaInfo() onError() called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("VIBAreaDomain", "getAreaInfo() onComplete() called");
                    }
                });
        return weather.getWeatherInfo(Common.location); //TODO Don't forget Common Class
    }

    public void updateFoodInfo(String foodInfo){
        localRepo.updateFoodInfo(foodInfo);
    }
}
