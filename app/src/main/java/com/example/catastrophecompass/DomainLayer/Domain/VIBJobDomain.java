package com.example.catastrophecompass.DomainLayer.Domain;

import android.util.Log;

import com.example.catastrophecompass.DataLayer.Model.Credentials;
import com.example.catastrophecompass.DataLayer.Model.VIBJobInfo;
import com.example.catastrophecompass.UILayer.Common.VIBJobInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class VIBJobDomain {
    private VIBLocalRepo localRepo;
    private VIBFBRepo FBRepo;

    public VIBJobDomain(VIBLocalRepo localRepo, VIBFBRepo FBRepo) {
        this.localRepo = localRepo;
        this.FBRepo = FBRepo;
    }

    public Credentials getCredentials(){
        return localRepo.getCredentials();
    }

    public void attachListeners(Credentials credentials){
        FBRepo.attachToVolunteerList(credentials.getCity(), credentials.getPlace(), credentials.getId());
    }

    public void getJobInfo(VIBJobInterface vibJobInterface){
        localRepo.getJobInfo()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<VIBJobInfo>() {
                    @Override
                    public void onNext(VIBJobInfo vibJobInfo) {
                        vibJobInterface.setDisplay(vibJobInfo);
                        Log.d("VIBJobDomain", "getJobInfo() onNext() called");
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        vibJobInterface.warnUser();
                        Log.d("VIBJobDomain", "getJobInfo() onError() called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("VIBJobDomain", "getJobInfo() onComplete() called");
                    }
                });
        localRepo.getPicturePath()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<String>() {
                    @Override
                    public void onNext(String picturePath) {
                        vibJobInterface.displayTLPicture(picturePath);
                        Log.d("VIBJobDomain", "getPicturePath() onNext() called");
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        vibJobInterface.warnUser();
                        Log.d("VIBJobDomain", "getPicturePath() onError() called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("VIBJobDomain", "getPicturePath() onComplete() called");
                    }
                });
    }

    public boolean quit(Credentials credentials){
        return FBRepo.deleteUserFB(credentials) && localRepo.deleteInfoLocally();
    }
}
