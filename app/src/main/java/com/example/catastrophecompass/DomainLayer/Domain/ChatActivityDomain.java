package com.example.catastrophecompass.DomainLayer.Domain;

import android.util.Log;

import com.example.catastrophecompass.DataLayer.FBRepository.ChatActivityFBRepo;
import com.example.catastrophecompass.DataLayer.LocalRepository.ChatActivityLocalRepo;
import com.example.catastrophecompass.DataLayer.Model.ChatItem;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class ChatActivityDomain {
    private ChatActivityLocalRepo localRepo;
    private ChatActivityFBRepo FBRepo;

    public ChatActivityDomain(ChatActivityLocalRepo localRepo, ChatActivityFBRepo FBRepo) {
        this.localRepo = localRepo;
        this.FBRepo = FBRepo;
    }

    public void setupDBConnection(String userName, String chattedUserName){
        FBRepo.setupDBConnection(userName, chattedUserName);
    }

    public void getMessages(ChatActivityInterface chatActivityInterface){
        final int[] lastMessageTime = {localRepo.getLatest().blockingGet()};
        localRepo.getMessage(lastMessageTime[0]) // TODO Handle backpressure and think about the logic of getting the last item
                .flatMap(Flowable::fromIterable).doOnNext(message -> lastMessageTime[0] = message.getTime())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<ChatItem>() {
                    @Override
                    public void onNext(ChatItem chatItem) {
                        chatActivityInterface.setDisplay(chatItem);
                        Log.d("ChatActivityDomain", "getMessages() onNext() called");
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        chatActivityInterface.warnUser();
                        Log.d("ChatActivityDomain", "getMessages() onError() is called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("ChatActivityDomain", "getMessages() onComplete() called");
                    }
                });
    }

    public boolean sendMessage(ChatItem chatItem){
        return FBRepo.sendMessage(chatItem);
    }
}
