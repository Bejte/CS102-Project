package com.example.catastrophecompass.UILayer.Logistics;
import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DomainLayer.Domain.LogisticMissionDomain;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LogisticMissionVM extends ViewModel {



    private LogisticMissionDomain logdomain;

    @Inject
    public LogisticMissionVM(LogisticMissionDomain logdomain) {
        this.logdomain = logdomain;
    }

    public void setUpDB(LogisticMissionInterface loginterface) {
        //Log.d(TAG, " here ");
        logdomain.setupDB(loginterface);
        //Log.d(TAG, "here too");
    }
    public void getLogisticInfo(LogisticMissionInterface loginterface) {
        //Log.d(TAG, " here ");
        logdomain.getLogisticInfo(loginterface);
        //Log.d(TAG, "here too");
    }
    public void getChecked(LogisticMissionInterface loginterface) {
        //Log.d(TAG, " here ");
        logdomain.getChecked(loginterface);
        //Log.d(TAG, "here too");
    }
    public void dropClicked() {
        logdomain.dropClicked();
    }
}
