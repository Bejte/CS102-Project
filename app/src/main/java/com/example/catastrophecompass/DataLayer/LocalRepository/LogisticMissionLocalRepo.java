package com.example.catastrophecompass.DataLayer.LocalRepository;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.catastrophecompass.DataLayer.Dao.CurrentUserDao;
import com.example.catastrophecompass.DataLayer.Dao.MissionDao;
import com.example.catastrophecompass.DataLayer.LocalDB;
import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LogisticMissionLocalRepo {

    private LocalDB db;
    private CurrentUserDao currentUserDao;
    private MissionDao missionDao;

    @Inject
    public LogisticMissionLocalRepo(LocalDB db) {
        this.db = db;
        this.currentUserDao = db.currentUserDao();
        this.missionDao = db.missionDao();
    }

    public Single<String> getDriverName() {
        return currentUserDao.getDriverName();
    }

    @SuppressLint("CheckResult")
    public void pushToLocal(LogisticInfo logisticInfo) {
        missionDao.recordLogisticInfo(logisticInfo)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d("LogisticMissionLocalRepo", "pushToLocal onComplete: ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("LogisticMissionLocalRepo", "pushToLocal onError: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }

    public Flowable<LogisticInfo> getLogisticInfo() {
        return missionDao.getLogisticInfo();
    }



}
