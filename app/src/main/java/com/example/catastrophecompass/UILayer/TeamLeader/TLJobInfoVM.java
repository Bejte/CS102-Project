package com.example.catastrophecompass.UILayer.TeamLeader;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.Credentials;
import com.example.catastrophecompass.DomainLayer.Domain.TLJobInfoDomain;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TLJobInfoVM extends ViewModel {
    private TLJobInfoDomain tlJobDomain;

    @Inject
    public TLJobInfoVM(TLJobInfoDomain tlJobDomain) {
        this.tlJobDomain = tlJobDomain;
    }
    public void setupDBConnection (Credentials cre){
        Log.d("TLJobInfoVM", "setupDBConnection() called");
        tlJobDomain.setupDBConnection(cre);
    }
    public void getTeamInfo(TeamInfoInterface teamInfoInterface) {
        Log.d("TLJobInfoVM", "getTeamInfo() called");
        tlJobDomain.getTeamInfo(teamInfoInterface);
    }
}