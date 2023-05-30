package com.example.catastrophecompass.DomainLayer.Domain;

import android.util.Log;

import com.example.catastrophecompass.DataLayer.FBRepository.TLJobFBRepo;
import com.example.catastrophecompass.DataLayer.LocalRepository.TLJobLocalRepo;
import com.example.catastrophecompass.DataLayer.Model.Credentials;
import com.example.catastrophecompass.DataLayer.Model.TeamInfo;
import com.example.catastrophecompass.UILayer.TeamLeader.TeamInfoInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class TLJobInfoDomain {
    private TLJobLocalRepo localRepo;
    private TLJobFBRepo FBRepo;

    public TLJobInfoDomain(TLJobLocalRepo localRepo, TLJobFBRepo FBRepo){
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
                        Log.d("TLJobInfoDomain", "getTeamInfo() onNext() called");
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        teamInfoInterface.warnUser();
                        Log.d("TLJobInfoDomain", "getTeamInfo() onError() called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("TLJobInfoDomain", "getTeamInfo() onComplete() called");
                    }
                });
    }
}
