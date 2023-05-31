package com.example.catastrophecompass.DomainLayer.Domain;

import android.util.Log;

import com.example.catastrophecompass.DataLayer.FBRepository.LogisticMissionFBRepo;
import com.example.catastrophecompass.DataLayer.LocalRepository.LogisticMissionLocalRepo;
import com.example.catastrophecompass.DataLayer.Model.InventoryList;
import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;
import com.example.catastrophecompass.RemoteDataRepository.CloudFunctionRepo.CloudRestApi;
import com.example.catastrophecompass.UILayer.Logistics.LogisticMissionInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class LogisticMissionDomain {
    private LogisticMissionLocalRepo localRepo;
    private LogisticMissionFBRepo FBRepo;
    private String driverName;
    private InventoryList inventory;
    private CloudRestApi restAPI;

    public LogisticMissionDomain(LogisticMissionLocalRepo localRepo, LogisticMissionFBRepo FBRepo, CloudRestApi restAPI) {
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
                        Log.d("LogisticMissionDomain", "getDriverName() onSuccess() called");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        logisticMissionInterface.warnUserForNoConnection(); // TODO check
                        Log.d("LogisticMissionDomain", "getDriverName() onError() called");
                    }
                });
    }

    public void getLogisticInfo(LogisticMissionInterface logisticMissionInterface){
        localRepo.getLogisticInfo()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<LogisticInfo>() {
                    @Override
                    public void onNext(LogisticInfo logisticInfo) {
                        inventory = logisticInfo.getInventoryList();
                        logisticMissionInterface.display(logisticInfo);
                        Log.d("LogisticMissionDomain", "getLogisticInfo() onNext() called");
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        logisticMissionInterface.warnUser();
                        Log.d("LogisticMissionDomain", "getLogisticInfo() onError() called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("LogisticMissionDomain", "getLogisticInfo() onComplete() called");
                    }
                });
    }

    public void getChecked(LogisticMissionInterface logisticMissionInterface){
        if (FBRepo.getChecked(driverName))
        {
            if (restAPI.decideDropPlace(inventory, driverName))
                logisticMissionInterface.notifyGetSuccess();
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
