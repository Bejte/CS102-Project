package com.example.catastrophecompass.DomainLayer.Domain;

import android.util.Log;

import com.example.catastrophecompass.DataLayer.FBRepository.ChatFragmentFBRepo;
import com.example.catastrophecompass.DataLayer.LocalRepository.ChatFragmentLocalRepo;
import com.example.catastrophecompass.DataLayer.Model.Credentials;
import com.example.catastrophecompass.DataLayer.Model.RecentChatItem;
import com.example.catastrophecompass.UILayer.Common.Chat.ChatFragmentInterface;

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

    public void setupDBConnection(String userName){
        FBRepo.setupDBConnection(userName);
    }

    public void getRecentChats(ChatFragmentInterface chatFragmentInterface){
        localRepo.getRecentChats()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<RecentChatItem>() {
                    @Override
                    public void onNext(RecentChatItem recentChatItem) {
                        chatFragmentInterface.setDisplay(recentChatItem);
                        Log.d("ChatFragmentDomain", "getRecentChats() onNext() called");
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        chatFragmentInterface.warnUser();
                        Log.d("ChatFragmentDomain", "getRecentChats() onError() called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("ChatFragmentDomain", "getRecentChats() onComplete() called");
                    }
                });
    }
}
