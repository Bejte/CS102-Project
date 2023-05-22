package com.example.catastrophecompass.DomainLayer.Domain;

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
        String teamName = FBRepo.attachToVolunteerList(credentials.getID());
        FBRepo.attachToTeam(credentials.getCity(), credentials.getPlace(), teamName);
    }

    public void getJobInfo(VIBJobInterface vibJobInterface){
        localRepo.getJobInfo()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<VIBJobInfo>() {
                    @Override
                    public void onNext(VIBJobInfo vibJobInfo) {
                        vibJobInterface.setDisplay(vibJobInfo);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        vibJobInterface.warnUser();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        localRepo.getPicturePath()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<String>() {
                    @Override
                    public void onNext(String picturePath) {
                        vibJobInterface.displayTLPicture(picturePath);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        vibJobInterface.warnUser();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public boolean quit(Credentials credentials){
        return FBRepo.deleteUserFB(credentials) && localRepo.deleteInfoLocally();
    }
}
