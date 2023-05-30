package com.example.catastrophecompass.UILayer.VolunteerInBase;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.Credentials;
import com.example.catastrophecompass.DomainLayer.Domain.VIBJobDomain;
import com.example.catastrophecompass.UILayer.VolunteerInBase.VIBJobInterface;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
@HiltViewModel
public class VIBJobInfoVM extends ViewModel {

    private VIBJobDomain vibJobDomain;
    private Credentials credentials;

    @Inject
    public VIBJobInfoVM(VIBJobDomain vibjobdomain) {
        this.vibJobDomain = vibjobdomain;
    }

    public void setupDBConnection(){
        Log.d("VIBJobInfoVM", "setupDBConnection() called");
        credentials = vibJobDomain.getCredentials();
        vibJobDomain.attachListeners(credentials);
    }

    public void getJobInfo(VIBJobInterface vibjobinterface) {
        Log.d("VIBJobInfoVM", "getJobInfo() called");
        vibJobDomain.getJobInfo(vibjobinterface);
        Log.d("VIBJobInfoVM", "getJobInfo() call finished");
    }
    public void quit(Credentials credentials){
        Log.d("VIBJobInfoVM", "quit() called");
        vibJobDomain.quit(credentials);
    }
}