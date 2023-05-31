package com.example.catastrophecompass.UILayer.HQOrganizer.Admin;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.HQAddEditMemberFBRepo;
import com.example.catastrophecompass.DataLayer.Model.HQO;
import com.example.catastrophecompass.DataLayer.Model.OrganizationAsNode;
import com.example.catastrophecompass.DataLayer.Model.TeamOrganizator;

import java.util.List;

@HiltViewModel
public class HQAddEditMemberVM extends ViewModel {
    private HQAddEditMemberFBRepo FBRepo;

    @Inject
    public HQAddEditMemberVM(HQAddEditMemberFBRepo FBRepo) {
        this.FBRepo = FBRepo;
    }



    public void addLogisticMember(Logistics logistics, String organizationName){
        Log.d("HQAddEditMemberVM", "addLogisticMember() called");
        return FBRepo.addLogisticMember(logistics,organizationName;
    }

    public void addHQOrganizator(HQO hqo, String organizationName){
        Log.d("HQAddEditMemberVM", "addLogisticMember() called");
        return FBRepo.addHQOrganizator(hqo,organizationName;
    }

    public void addTeamOrganizator(TeamOrganizator to, String organizationName){
        Log.d("HQAddEditMemberVM", "addLogisticMember() called");
        return FBRepo.addTeamOrganizator(to,organizationName;
    }
}
