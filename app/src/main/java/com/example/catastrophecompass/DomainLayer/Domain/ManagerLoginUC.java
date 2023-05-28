package com.example.catastrophecompass.DomainLayer.Domain;

import com.example.catastrophecompass.DataLayer.Dao.CurrentUserDao;
import com.example.catastrophecompass.DataLayer.Model.User;
import com.example.catastrophecompass.DataLayer.Model.UserLogin;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ManagerLoginUC {
    private ManagerLoginFBRepo FBRepo;
    private CurrentUserDao currentUserDao;

    public ManagerLoginUC(ManagerLoginFBRepo FBRepo, CurrentUserDao currentUserDao) {
        this.FBRepo = FBRepo;
        this.currentUserDao = currentUserDao;
    }

    public String validateLogin(UserLogin userLogin){
        User user = FBRepo.validateLogin(userLogin);
        if (user!=null)
        {
            currentUserDao.recordUser(user)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() { // TODO Complete methods
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onComplete() {

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }
                    });
            return user.getUserType();
        }
        else
            return null;
    }
}
