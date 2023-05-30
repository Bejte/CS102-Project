package com.example.catastrophecompass.DataLayer.LocalRepository;

import android.util.Log;

import com.example.catastrophecompass.DataLayer.Dao.TLJobLocalDao;
import com.example.catastrophecompass.DataLayer.LocalDB;
import com.example.catastrophecompass.DataLayer.Model.TeamInfo;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TLJobLocalRepo {

    private final LocalDB db;
    private TLJobLocalDao tlJobLocalDao;

    @Inject
    public TLJobLocalRepo(LocalDB db) {
        this.db = db;
        this.tlJobLocalDao = db.tlJobLocalDao();
    }

    public void pushToLocal(TeamInfo teamInfo) {
        tlJobLocalDao.insertTeamInfo(teamInfo)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d("TLJobLocalRepo", " tlJobLocalDao.insertTeamInfo onComplete: ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("TLJobLocalRepo", " tlJobLocalDao.insertTeamInfo onError: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }

    public Flowable<TeamInfo> getTeamInfo() {
        return tlJobLocalDao.getTeamInfo();
    }
}
