package com.example.catastrophecompass.UILayer.HQOrganizer;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.Contact;
import com.example.catastrophecompass.DataLayer.Model.HQO;
import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;
import com.example.catastrophecompass.DataLayer.Model.TeamOrganizator;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HQAddEditMemberVM extends ViewModel {
    private HQAddEditMemberFBRepo FBRepo;

    @Inject
    public HQAddEditMemberVM(HQAddEditMemberFBRepo FBRepo) {
        this.FBRepo = FBRepo;
    }

    public void addLogisticMember(LogisticInfo logisticInfo, String organizationName){
        Log.d("HQAddEditMemberVM", "addLogisticMember() called");
        FBRepo.addLogisticMember(logisticInfo, organizationName);
    }

    public void addHQOrganizer(HQO hqo, String organizationName){
        Log.d("HQAddEditMemberVM", "addHQOrganizer() called");
        FBRepo.addHQOrganizer(hqo, organizationName);
    }

    public void addTeamOrganizer(TeamOrganizator teamOrganizator, String organizationName){
        Log.d("HQAddEditMemberVM", "addTeamOrganizer() called");
        FBRepo.addTeamOrganizer(teamOrganizator, organizationName);
    }

    public Member getPreDisplay(Contact contact){ // TODO create Member class or find another way
        Log.d("HQAddEditMemberVM", "getPreDisplay() called");
        return FBRepo.getPreDisplay(contact);
    }

    public void editLogisticMember(LogisticInfo logisticInfo, String organizationName){
        Log.d("HQAddEditMemberVM", "editLogisticMember() called");
        FBRepo.editLogisticMember(logisticInfo, organizationName);
    }

    public void editHQOrganizer(HQO hqo, String organizationName){
        Log.d("HQAddEditMemberVM", "editHQOrganizer() called");
        FBRepo.editHQOrganizer(hqo, organizationName);
    }

    public void editTeamOrganizer(TeamOrganizator teamOrganizator, String organizationName){
        Log.d("HQAddEditMemberVM", "editTeamOrganizer() called");
        FBRepo.editTeamOrganizer(teamOrganizator, organizationName);
    }
}
