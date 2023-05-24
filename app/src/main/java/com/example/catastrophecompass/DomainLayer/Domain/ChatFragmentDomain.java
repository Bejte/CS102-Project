package com.example.catastrophecompass.DomainLayer.Domain;

import com.example.catastrophecompass.DataLayer.Model.Credentials;
import com.example.catastrophecompass.DataLayer.Model.RecentChatItem;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class ChatFragmentDomain {
    private ChatFragmentLocalRepo localRepo;
    private ChatFragmentFBRepo FBRepo;

    public ChatFragmentDomain(ChatFragmentLocalRepo localRepo, ChatFragmentFBRepo FBRepo) {
        this.localRepo = localRepo;
        this.FBRepo = FBRepo;
    }

    public void setupDBConnection(Credentials credentials){
        FBRepo.attachListener(credentials);
    }

    public void getRecentChats(ChatFragmentInterface chatFragmentInterface){
        localRepo.getRecentChats()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<RecentChatItem>() {
                    @Override
                    public void onNext(RecentChatItem recentChatItem) {
                        chatFragmentInterface.setDisplay(recentChatItem);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        chatFragmentInterface.warnUser();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
