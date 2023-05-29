package com.example.catastrophecompass.DataLayer.LocalRepository;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.catastrophecompass.DataLayer.Dao.ChatFragmentDao;
import com.example.catastrophecompass.DataLayer.LocalDB;
import com.example.catastrophecompass.DataLayer.Model.RecentChatItem;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ChatFragmentLocalRepo {

    private LocalDB db;
    private ChatFragmentDao chatFragmentDao;

    public ChatFragmentLocalRepo(LocalDB db) {
        this.db = db;
        this.chatFragmentDao = db.chatFragmentDao();
    }

    @SuppressLint("CheckResult")
    public void pushToLocal(RecentChatItem chat) {
        chatFragmentDao.pushToLocal(chat)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d("ChatFragmentLocalRepo", "pushToLocal onComplete: ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ChatFragmentLocalRepo", "pushToLocal onError: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }

    public Flowable<RecentChatItem> getRecentChats() {
        return chatFragmentDao.getRecentChats();
    }
}
