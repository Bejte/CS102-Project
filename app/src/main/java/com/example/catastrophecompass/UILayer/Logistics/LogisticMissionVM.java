package com.example.catastrophecompass.UILayer.Logistics;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DomainLayer.Domain.LogisticMissionDomain;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LogisticMissionVM extends ViewModel {



    private LogisticMissionDomain domain;

    @Inject
    public LogisticMissionVM(LogisticMissionDomain domain) {
        this.domain = domain;
    }

    public void setupDB(LogisticMissionInterface logisticMissionInterface) {
        Log.d("LogisticMissionVM", "setupDB() called");
        domain.setupDB(logisticMissionInterface);
    }
    public void getLogisticInfo(LogisticMissionInterface logisticMissionInterface) {
        Log.d("LogisticMissionVM", "getLogisticInfo() called");
        domain.getLogisticInfo(logisticMissionInterface);
    }
    public void getChecked(LogisticMissionInterface logisticMissionInterface) {
        Log.d("LogisticMissionVM", "getChecked() called");
        domain.getChecked(logisticMissionInterface);
    }
    public void dropClicked(LogisticMissionInterface logisticMissionInterface) {
        domain.dropClicked(logisticMissionInterface);
    }
}
