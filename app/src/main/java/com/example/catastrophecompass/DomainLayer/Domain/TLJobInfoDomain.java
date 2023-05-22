package com.example.catastrophecompass.DomainLayer.Domain;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class TLJobInfoDomain {
    private TLJobInfoLocalRepo localRepo;
    private TLJobInfoFBRepo FBRepo;

    public TLJobInfoDomain(TLJobInfoLocalRepo localRepo, TLJobInfoFBRepo FBRepo){
        this.localRepo = localRepo;
        this.FBRepo = FBRepo;
    }

    public void setupDBConnection(Credentials credentials){
        FBRepo.attachListener(credentials);
    }

    public void getTeamInfo(TeamInfoInterface teamInfoInterface){
        localRepo.getTeamInfo()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<TeamInfo>() {
                    @Override
                    public void onNext(TeamInfo teamInfo) {
                        teamInfoInterface.setDisplay(teamInfo);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        teamInfoInterface.warnUser();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
