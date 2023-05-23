package com.example.catastrophecompass.DomainLayer.Domain;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class VIBAreaDomain {
    private VIBLocalRepo localRepo;
    private VIBFBRepo FBRepo;
    private WeatherAPI weather;

    public VIBAreaDomain(VIBLocalRepo localRepo, VIBFBRepo FBRepo, WeatherAPI weather) {
        this.localRepo = localRepo;
        this.FBRepo = FBRepo;
        this.weather = weather;
    }

    public WorkplaceWeather getAreaInfo(VIBAreaInterface vibAreaInterface){
        localRepo.getAreaInfo()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<VIBAreaInfo>() {
                    @Override
                    public void onNext(VIBAreaInfo vibAreaInfo) {
                        vibAreaInterface.setDisplay(vibAreaInfo);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        vibAreaInterface.warnUser();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return weather.getWeatherInfo(Common.location); //TODO Don't forget Common Class
    }

    public void updateFoodInfo(String foodInfo){
        localRepo.updateFoodInfo(foodInfo);
    }
}