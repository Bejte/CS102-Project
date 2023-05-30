package com.example.catastrophecompass.UILayer.Common.Chat;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.ChatItem;
import com.example.catastrophecompass.DomainLayer.Domain.ChatActivityDomain;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ChatActivityVM extends ViewModel {
    private ChatActivityDomain domain;

    @Inject
    public ChatActivityVM(ChatActivityDomain domain){
        this.domain = domain;
    }

    public void setupDBConnection(String userName, String chattedUserName){
        Log.d("ChatActivityVM", "setupDBConnection() called");
        domain.setupDBConnection(userName, chattedUserName);
    }

    public void getMessages(ChatActivityInterface chatActivityInterface){
        Log.d("ChatActivityVM", "getMessages() called");
        domain.getMessages(chatActivityInterface);
    }

    public boolean sendMessage(ChatItem chatItem){
        Log.d("ChatActivityVM", "sendMessage() called");
        return domain.sendMessage(chatItem);
    }
}
