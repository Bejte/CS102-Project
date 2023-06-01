package com.example.catastrophecompass.UILayer.Common.Chat;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.ContactsFBRepo;
import com.example.catastrophecompass.DataLayer.Model.Contact;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ContactsVM extends ViewModel {
    private ContactsFBRepo FBRepo;

    @Inject
    public ContactsVM(ContactsFBRepo FBRepo){
        this.FBRepo = FBRepo;
    }

    public List<Contact> getContacts(String organizationName, String userName){
        Log.d("ContactsVM", "getContacts() called");
        return FBRepo.getContact(organizationName);
    }

    public boolean startChat(String userName, String contactName){
        Log.d("ContactsVM", "startChat() called");
        return FBRepo.startChat(userName, contactName);
    }
}
