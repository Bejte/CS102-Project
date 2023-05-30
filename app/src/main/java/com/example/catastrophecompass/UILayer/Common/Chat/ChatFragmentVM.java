package com.example.catastrophecompass.UILayer.Common.Chat;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.Credentials;
import com.example.catastrophecompass.DomainLayer.Domain.ChatFragmentDomain;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ChatFragmentVM extends ViewModel {
    private ChatFragmentDomain domain;

    @Inject
    public ChatFragmentVM(ChatFragmentDomain domain){
        this.domain = domain;
    }

    public void setupDBConnection(Credentials credentials){
        Log.d("ChatFragmentVM", "setupDBConnection() called");
        domain.setupDBConnection(credentials);
    }

    public void getRecentChats(ChatFragmentInterface chatFragmentInterface){
        Log.d("ChatFragmentVM", "getRecentChats() called");
        domain.getRecentChats(chatFragmentInterface);
    }
}
