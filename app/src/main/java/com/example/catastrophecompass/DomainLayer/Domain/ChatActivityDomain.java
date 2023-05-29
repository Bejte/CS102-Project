package com.example.catastrophecompass.DomainLayer.Domain;

import com.example.catastrophecompass.DataLayer.Model.ChatItem;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
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
        localRepo.getMessages() // TODO Handle backpressure and think about the logic of getting the last item
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<ChatItem>() {
                    @Override
                    public void onNext(ChatItem chatItem) {
                        chatActivityInterface.setDisplay(chatItem);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        chatActivityInterface.warnUser();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public boolean sendMessage(ChatItem chatItem){
        return FBRepo.sendMessage(chatItem);
    }
}
