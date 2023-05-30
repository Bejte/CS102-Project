package com.example.catastrophecompass.UILayer.TeamLeader;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TLJobInfoVM extends ViewModel {



    private TLJobDomain tlJobDomain;

    @Inject
    public TLJobInfoVM(TLJobDomain tlJobDomain) {
        this.tlJobDomain = tlJobDomain;
    }
    public void setDBConnection (Credentials cre){
        tlJobDomain.setDBConnection(cre);
    }
    public void getTeamInfo(TeamInfoInterface teamInfoInterface) {
        //Log.d(TAG, " here ");
        tlJobDomain.getTeamInfo(teamInfoInterface);
        //Log.d(TAG, "here too");
    }
}