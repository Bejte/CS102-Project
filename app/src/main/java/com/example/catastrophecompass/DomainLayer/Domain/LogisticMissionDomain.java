package com.example.catastrophecompass.DomainLayer.Domain;

import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class LogisticMissionDomain {
    private LogisticMissionLocalRepo localRepo;
    private LogisticMissionFBRepo FBRepo;
    private String driverName;
    private Inventory inventory;
    private CloudRestAPI restAPI;

    public LogisticMissionDomain(LogisticMissionLocalRepo localRepo, LogisticMissionFBRepo FBRepo, CloudRestAPI restAPI) {
        this.localRepo = localRepo;
        this.FBRepo = FBRepo;
        this.restAPI = restAPI;
    }

    public void setupDB(LogisticMissionInterface logisticMissionInterface){
        // TODO Test the difference between DisposableSubscriber and DisposableObserver
        localRepo.getDriverName()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<String>() {
                    @Override
                    public void onSuccess(@NonNull String s) {
                        driverName = s;
                        FBRepo.getLogisticInfo(driverName);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        logisticMissionInterface.warnUserForNoConnection();
                    }
                });
    }

    public void getLogisticInfo(LogisticMissionInterface logisticMissionInterface){
        localRepo.getLogisticInfo()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<LogisticInfo>() {
                    @Override
                    public void onNext(LogisticInfo logisticInfo) {
                        inventory = logisticInfo.getInventory();
                        logisticMissionInterface.display(logisticInfo);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        logisticMissionInterface.warnUser();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getChecked(LogisticMissionInterface logisticMissionInterface){
        if (FBRepo.getChecked(driverName))
        {
            if (restAPI.decideDropPlace(inventory, driverName))
                logisticMissionInterface.notifyGetSucces();
            else
                logisticMissionInterface.notifyGetFailed();
        }
        else
            logisticMissionInterface.warnUser();
    }

    public void dropClicked(LogisticMissionInterface logisticMissionInterface){
        if (FBRepo.dropClicked(driverName))
            logisticMissionInterface.notifyDropSuccess();
        else
            logisticMissionInterface.notifyDropFailed();
    }
}
