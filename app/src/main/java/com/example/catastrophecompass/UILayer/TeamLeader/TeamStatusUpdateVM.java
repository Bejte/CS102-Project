package com.example.catastrophecompass.UILayer.TeamLeader;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.FBRepository.TeamStatusUpdateFBRepo;
import com.example.catastrophecompass.DataLayer.Model.VolunteerInfo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TeamStatusUpdateVM extends ViewModel {



    private TeamStatusUpdateFBRepo tsupdateFB;

    @Inject
    public TeamStatusUpdateVM(TeamStatusUpdateFBRepo tsupdateFB) {
        this.tsupdateFB = tsupdateFB;
    }
    public boolean updateVolunteerInfo (String city, String place, String team, VolunteerInfo vinfo) {
        Log.d("TeamStatusUpdateVM", "updateVolunteerInfo() called");
        return tsupdateFB.updateVolunteerInfo (city, place, team, vinfo); // TODO parameter mismatch
    }

}