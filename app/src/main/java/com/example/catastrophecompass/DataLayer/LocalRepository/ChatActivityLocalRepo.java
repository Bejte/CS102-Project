package com.example.catastrophecompass.DataLayer.LocalRepository;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.catastrophecompass.DataLayer.Dao.ChatActivityDao;
import com.example.catastrophecompass.DataLayer.LocalDB;
import com.example.catastrophecompass.DataLayer.Model.ChatItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ChatActivityLocalRepo {

    private ChatPictureLocalStorage pictureLocal;
    private LocalDB localDB;
    private ChatActivityDao chatActivityDao;

    @Inject
    public ChatActivityLocalRepo(ChatPictureLocalStorage pictureLocal, LocalDB localDB) {
        this.pictureLocal = pictureLocal;
        this.localDB = localDB;
        this.chatActivityDao = localDB.chatActivityDao();
    }

    @SuppressLint("CheckResult")
    public void pushToLocal(ChatItem chatItem) {
        String filepath = pictureLocal.putImageToLocal(chatItem.getPictureUrl());
        chatItem.setPicturePath(filepath);

        chatActivityDao.pushToLocal(chatItem)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d("ChatActivityLocalRepo", "pushToLocal onComplete: ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ChatActivityLocalRepo", "pushToLocal onError: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }

    public Flowable<List<ChatItem>> getMessage(int lastMessageTime) {
        return chatActivityDao.getMessages(lastMessageTime);
    }

    public Single<Integer> getLatest() {
        return chatActivityDao.getLatest();
    }



}
