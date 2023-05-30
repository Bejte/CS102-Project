package com.example.catastrophecompass.UILayer.Common;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.Credentials;
import com.example.catastrophecompass.DomainLayer.Domain.VIBJobDomain;

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
        credentials = vibJobDomain.getCredentials();
        vibJobDomain.attachListeners(credentials);
    }

    public void getJobInfo(VIBJobInterface vibjobinterface) {
        //Log.d(TAG, " here ");
        vibJobDomain.getJobInfo(vibjobinterface);
        //Log.d(TAG, "here too");
    }
    public void quit(Credentials credentials){
        vibJobDomain.quit(credentials);
    }
}