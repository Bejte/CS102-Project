package com.example.catastrophecompass.DataLayer.LocalRepository;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.catastrophecompass.DataLayer.Dao.VIBDao;
import com.example.catastrophecompass.DataLayer.LocalDB;
import com.example.catastrophecompass.DataLayer.Model.Credentials;
import com.example.catastrophecompass.DataLayer.Model.FilePath;
import com.example.catastrophecompass.DataLayer.Model.VIBJobInfo;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class VIBLocalRepo {


    private final LocalDB db;
    private VIBDao vibDao;
    private VIBTLProfilePicL vibtlProfilePicL;

    @Inject
    public VIBLocalRepo(LocalDB db, VIBDao vibDao, VIBTLProfilePicL vibtlProfilePicL) {
        this.db = db;
        this.vibDao = vibDao;
        this.vibtlProfilePicL = vibtlProfilePicL;
    }

    @SuppressLint("CheckResult")
    public void recordCredentials(Credentials credentials) {
       vibDao.recordCredentials(credentials)
               .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
               .subscribeWith(new DisposableCompletableObserver() {
                   @Override
                   public void onComplete() {
                       Log.d(TAG, "VIBLocalRepo recordCredentials onComplete: ");
                   }

                   @Override
                   public void onError(@NonNull Throwable e) {
                       Log.d(TAG, "VIBLocalRepo recordCredentials onError: " + e.getMessage());
                       e.printStackTrace();
                   }
               });

    }

    public Credentials getCredentials() {
        AtomicReference<Credentials> credentialsAtomicReference = new AtomicReference<>();
        vibDao.getCredentials().
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Credentials>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "VIBLocalRepo getCredentials onSubscribe: ");
                    }

                    @Override
                    public void onSuccess(@NonNull Credentials credentials) {
                        Log.d(TAG, "VIBLocalRepo getCredentials  onSuccess: teamName: " + credentials.getTeamName());
                        credentialsAtomicReference.set(credentials);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Log.d(TAG, "VIBLocalRepo getCredentials  onError: " +e.getMessage());
                        e.printStackTrace();
                    }
                });

        return credentialsAtomicReference.get();
    }

    @SuppressLint("CheckResult")
    public void pushToLocal(VIBJobInfo vibJobInfo) {

        vibDao.pushToLocal(vibJobInfo).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {

                        Log.d(TAG, "VIBLocalRepo pushToLocal vibDao.pushToLocal onComplete: ");
                        String filePath = vibtlProfilePicL.recordTLPicToLocal(vibJobInfo.getTeamLeaderPicUrl());
                        FilePath fp = new FilePath(filePath);

                        vibDao.recordFilePath(fp).
                                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new DisposableCompletableObserver() {
                                    @Override
                                    public void onComplete() {
                                        Log.d(TAG, "VIBLocalRepo pushToLocal vibDao.recordFilePath onComplete: ");
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        Log.d(TAG, "VIBLocalRepo pushToLocal vibDao.recordFilePath onError: " + e.getMessage());
                                        e.printStackTrace();

                                    }
                                });
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "VIBLocalRepo pushToLocal vibDao.pushToLocal onError: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }

    public Flowable<VIBJobInfo> getJobInfo() {
        return vibDao.getJobInfo();
    }

    public Flowable<String> getPicturePath() {
        return vibDao.getPicturePath();
    }

    @SuppressLint("CheckResult")
    public boolean deleteInfoLocally() {
        AtomicBoolean status = new AtomicBoolean(false);

        vibDao.deleteVIBJobInfo()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "VIBLocalRepo vibDao.deleteVIBJobInfo onComplete: ");

                        AtomicReference<String> filePathAsAtomic = new AtomicReference<>();

                        vibDao.getPicturePath()
                                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new DisposableSubscriber<String>() {
                                    @Override
                                    public void onNext(String s) {
                                        Log.d(TAG, "VIBLocalRepo vibDao.getPicturePath onNext: Process started");
                                        filePathAsAtomic.set(s);

                                        vibDao.deleteTLPicPath()
                                                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                                .subscribeWith(new DisposableCompletableObserver() {
                                                    @Override
                                                    public void onComplete() {
                                                        Log.d(TAG, "VIBLocalRepo vibDao.deleteTLPicPath onComplete: ");

                                                        status.set(vibtlProfilePicL.deleteTLPic(filePathAsAtomic.get()));
                                                    }

                                                    @Override
                                                    public void onError(@NonNull Throwable e) {
                                                        Log.d(TAG, "VIBLocalRepo vibDao.deleteTLPicPath onError: " + e.getMessage());
                                                        e.printStackTrace();
                                                    }
                                                });

                                        // Todo: This is truly risky stuff. I hope it will work. But it won't. I don't want to write a concurrent scheduler.
                                        //       Be careful when testing here.
                                        Log.d(TAG, "VIBLocalRepo vibDao.getPicturePath onNext: onComplete statement is reached. ");
                                        onComplete();
                                        Log.d(TAG, "VIBLocalRepo vibDao.getPicturePath onNext: I just wonder if it will execute itself ");
                                    }

                                    @Override
                                    public void onError(Throwable t) {
                                        Log.d(TAG, "VIBLocalRepo vibDao.getPicturePath onError: " + t.getMessage());
                                        t.printStackTrace();
                                    }

                                    @Override
                                    public void onComplete() {
                                        Log.d(TAG, "VIBLocalRepo vibDao.getPicturePath onComplete: ");
                                    }
                                });


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "VIBLocalRepo vibDao.deleteVibJobInfo onError: " + e.getMessage());
                        e.printStackTrace();
                    }
                });

        return status.get();
        
    }




}
