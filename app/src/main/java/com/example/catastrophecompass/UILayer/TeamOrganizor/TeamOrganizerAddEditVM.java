package com.example.catastrophecompass.UILayer.TeamOrganizor;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.TeamOrganizatorAddEditFBRepo;
import com.example.catastrophecompass.DataLayer.Model.TeamInfo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TeamOrganizerAddEditVM extends ViewModel {
    private TeamOrganizatorAddEditFBRepo FBRepo;

    @Inject
    public TeamOrganizerAddEditVM(TeamOrganizatorAddEditFBRepo FBRepo) {
        this.FBRepo = FBRepo;
    }

    public void addTeam(String city, String place, TeamInfo team){
        Log.d("TeamOrganizerAddEditVM", "addTeam() called");
        FBRepo.addTeam(city, place, team);
    }

    public void discardTeam(String city, String place, TeamInfo team){
        Log.d("TeamOrganizerAddEditVM", "discardTeam() called");
        FBRepo.discardTeam(city, place, team);
    }
}
