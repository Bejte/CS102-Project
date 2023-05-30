package com.example.catastrophecompass.UILayer.Common;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.GeneralInfoFBRepo;
import com.example.catastrophecompass.DataLayer.Model.OrganizationAsNode;
import com.example.catastrophecompass.DataLayer.Model.VolunteerInfo;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class GeneralInfoVM extends ViewModel {
    private GeneralInfoFBRepo FBRepo;

    @Inject
    public GeneralInfoVM(GeneralInfoFBRepo FBRepo){
        this.FBRepo = FBRepo;
    }

    public VolunteerInfo getGeneralInformation(String organizationName){
        Log.d("GeneralInfoVM", "getGeneralInformation() called");
        return FBRepo.getGeneralInformation(organizationName);
    }

    public List<OrganizationAsNode> getSubOrganizations(String organizationName){
        Log.d("GeneralInfoVM", "getSubOrganizations() called");
        return FBRepo.getSubOrganizations(organizationName);
    }
}
