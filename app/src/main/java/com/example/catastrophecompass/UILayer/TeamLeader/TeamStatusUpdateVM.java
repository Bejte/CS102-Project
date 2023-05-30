package com.example.catastrophecompass.UILayer.TeamLeader;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TeamStatusUpdateVM extends ViewModel {



    private TeamStatusUpdateFBRepo tsupdateFB;

    @Inject
    public TeamStatusUpdateVM(TeamStatusUpdateFBRepo tsupdateFB) {
        this.tsupdateFB = tsupdateFB;
    }
    public void updateVolunteerInfo (VolunteerInfo vinfo) {
        tsupdateFB.updateVolunteerInfo (vinfo);
    }

}