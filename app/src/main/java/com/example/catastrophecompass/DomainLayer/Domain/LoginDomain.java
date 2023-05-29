package com.example.catastrophecompass.DomainLayer.Domain;

import android.util.Log;

import com.example.catastrophecompass.DataLayer.Dao.CurrentUserDao;
import com.example.catastrophecompass.DataLayer.Dao.VIBDao;
import com.example.catastrophecompass.DataLayer.Model.User;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class LoginDomain {
    private CurrentUserDao currentUserDao;
    private VIBDao vibDao;
    private DisposableSubscriber<List<Integer>> vibDaoSubscriber;
    private DisposableSubscriber<List<User>> currentUserDaoSubscriber;

    public LoginDomain(CurrentUserDao currentUserDao, VIBDao vibDao) {
        this.currentUserDao = currentUserDao;
        this.vibDao = vibDao;
    }

    public boolean isLoggedIn(){
        //TODO Using Single or simple return
        final boolean[] returnType = new boolean[1];
        currentUserDao.getCurrentUser()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(currentUserDaoSubscriber = new DisposableSubscriber<List<User>>() {
                    @Override
                    public void onNext(List<User> users) {
                        if (users.size() == 0)
                        {
                            vibDao.getIdAsList()
                                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(vibDaoSubscriber = new DisposableSubscriber<List<Integer>>() {
                                        @Override
                                        public void onNext(List<Integer> integers) {
                                            if (integers.size() == 0)
                                                returnType[0] = false;
                                            else
                                                returnType[0] = true;
                                            Log.d("LoginDomain", "vibDao.getIdAsList() onNext() called");
                                        }

                                        @Override
                                        public void onError(Throwable t) {
                                            t.printStackTrace();
                                            Log.d("LoginDomain", "vibDao.getCurrentUser() onError() called");
                                        }

                                        @Override
                                        public void onComplete() {
                                            Log.d("LoginDomain", "vibDao.getIdAsList() onComplete() called");
                                        }
                                    });
                        }
                        else
                            returnType[0] = true;
                        Log.d("LoginDomain", "currentUserDao.getCurrentUser() onNext() called");
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        Log.d("LoginDomain", "currentUserDao.getCurrentUser() onError() called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("LoginDomain", "currentUserDao.getCurrentUser() onComplete() called");
                    }
                });
        return returnType[0];
    }

    public void killDataFlow(){
        if (vibDaoSubscriber != null)
            vibDaoSubscriber.dispose();
        if (currentUserDaoSubscriber != null)
            currentUserDaoSubscriber.dispose();
    }
}
