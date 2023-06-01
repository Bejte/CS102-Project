package com.example.catastrophecompass.UILayer.HQOrganizer.Admin;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.HQAddEditMemberFBRepo;
import com.example.catastrophecompass.DataLayer.Model.Contact;
import com.example.catastrophecompass.DataLayer.Model.HQO;
import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;
import com.example.catastrophecompass.DataLayer.Model.Member;
import com.example.catastrophecompass.DataLayer.Model.TeamOrganizator;
import com.example.catastrophecompass.DataLayer.Model.UserLogin;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HQAddEditMemberVM extends ViewModel {
    private HQAddEditMemberFBRepo FBRepo;

    @Inject
    public HQAddEditMemberVM(HQAddEditMemberFBRepo FBRepo) {
        this.FBRepo = FBRepo;
    }

    public void addLogisticMember(LogisticInfo logisticInfo, String organizationName, UserLogin userLogin){
        Log.d("HQAddEditMemberVM", "addLogisticMember() called");
        FBRepo.addLogisticMember(logisticInfo, organizationName, userLogin);
    }

    public void addHQOrganizer(HQO hqo, String organizationName){
        Log.d("HQAddEditMemberVM", "addHQOrganizer() called");
        FBRepo.addHQOrganizator(hqo, organizationName);
    }

    public void addTeamOrganizer(TeamOrganizator teamOrganizator, String organizationName){
        Log.d("HQAddEditMemberVM", "addTeamOrganizer() called");
        FBRepo.addTeamOrganizatorMember(teamOrganizator, organizationName);
    }

    public Member getPreDisplay(Contact contact){ // TODO create Member class or find another way
        Log.d("HQAddEditMemberVM", "getPreDisplay() called");
        return FBRepo.getPredisplay(contact);
    }

    public void editLogisticMember(LogisticInfo logisticInfo, String organizationName, UserLogin userLogin){
        Log.d("HQAddEditMemberVM", "editLogisticMember() called");
        FBRepo.editLogisticMember(logisticInfo, organizationName, userLogin);
    }

    public void editHQOrganizer(HQO hqo, String organizationName){
        Log.d("HQAddEditMemberVM", "editHQOrganizer() called");
        FBRepo.editHQOrganizator(hqo, organizationName);
    }

    public void editTeamOrganizer(TeamOrganizator teamOrganizator, String organizationName){
        Log.d("HQAddEditMemberVM", "editTeamOrganizer() called");
        FBRepo.editTeamOrganizatorMember(teamOrganizator, organizationName);
    }
}
