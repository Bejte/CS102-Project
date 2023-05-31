package com.example.catastrophecompass.UILayer.HQOrganizer.Admin;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.HQAddEditMemberFBRepo;
import com.example.catastrophecompass.DataLayer.Model.HQO;
import com.example.catastrophecompass.DataLayer.Model.LogisticInfo;
import com.example.catastrophecompass.DataLayer.Model.OrganizationAsNode;
import com.example.catastrophecompass.DataLayer.Model.TeamOrganizator;
import com.example.catastrophecompass.DataLayer.Model.UserLogin;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HQAddEditMemberVM extends ViewModel {
    private HQAddEditMemberFBRepo FBRepo;

    @Inject
    public HQAddEditMemberVM(HQAddEditMemberFBRepo FBRepo) {
        this.FBRepo = FBRepo;
    }



    public void addLogisticMember(LogisticInfo logistics, String organizationName, UserLogin userLogin){ // TODO Add this to View
        Log.d("HQAddEditMemberVM", "addLogisticMember() called");
        return FBRepo.addLogisticMember(logistics,organizationName, userLogin);
    }

    public void addHQOrganizator(HQO hqo, String organizationName){
        Log.d("HQAddEditMemberVM", "addLogisticMember() called");
        return FBRepo.addHQOrganizator(hqo,organizationName);
    }

    public void addTeamOrganizator(TeamOrganizator to, String organizationName){
        Log.d("HQAddEditMemberVM", "addLogisticMember() called");
        return FBRepo.addTeamOrganizator(to,organizationName);
    }
}
