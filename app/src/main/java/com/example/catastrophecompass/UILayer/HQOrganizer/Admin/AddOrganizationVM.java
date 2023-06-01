package com.example.catastrophecompass.UILayer.HQOrganizer.Admin;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.AddOrganizationFBRepo;
import com.example.catastrophecompass.DataLayer.Model.OrganizationAsNode;
import com.example.catastrophecompass.DataLayer.Model.TeamOrganization;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddOrganizationVM extends ViewModel {
    private AddOrganizationFBRepo FBRepo;

    @Inject
    public AddOrganizationVM(AddOrganizationFBRepo FBRepo) {
        this.FBRepo = FBRepo;
    }

    public List<OrganizationAsNode> getBuildsDownList(String baseOrganization){ // TODO assumed baseOrganization was String, ask
        Log.d("AddOrganizationVM", "getBuildsDownList() called");
        return FBRepo.getBuildsDownList(baseOrganization);
    }

    public boolean createFieldOrganization(OrganizationAsNode organization, OrganizationAsNode lastClicked){
        Log.d("AddOrganizationVM", "createFieldOrganization() called");
        return FBRepo.createFieldOrganization(organization, lastClicked.getName());
    }

    public boolean createHQOrganization(OrganizationAsNode organization, OrganizationAsNode lastClicked){
        Log.d("AddOrganizationVM", "createHQOrganization() called");
        return FBRepo.createHQOrganization(organization, lastClicked.getName());
    }

    public boolean createTeamOrganization(OrganizationAsNode organization, OrganizationAsNode lastClicked){
        Log.d("AddOrganizationVM", "createTeamOrganization() called");
        return FBRepo.createTeamOrganization((TeamOrganization) organization, lastClicked.getName());
    }
}
