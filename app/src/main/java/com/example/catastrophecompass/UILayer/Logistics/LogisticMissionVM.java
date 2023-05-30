package com.example.catastrophecompass.UILayer.Logistics;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import static androidx.fragment.app.FragmentManager.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.room.Insert;

import com.example.catastrophecompass.DataLayer.Model.WItem;
import com.example.catastrophecompass.DomainLayer.Domain.LogisticMissionDomain;
import com.example.catastrophecompass.DomainLayer.Domain.VIBAreaDomain;
import com.example.catastrophecompass.UILayer.Common.VIBAreaInterface;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.Disposable;
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
