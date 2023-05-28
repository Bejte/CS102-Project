package com.example.catastrophecompass.UILayer.Common;

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

import com.example.catastrophecompass.DataLayer.Model.Credentials;
import com.example.catastrophecompass.DataLayer.Model.WItem;
import com.example.catastrophecompass.DomainLayer.Domain.VIBAreaDomain;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.Disposable;
@HiltViewModel
public class VIBJobInfoVM extends ViewModel {

    private UC uc;

    private VIBInfoDomain vıbjobdomain;

    @Inject
    public VIBJobInfoVM(UC uc) {
        this.uc = uc;
    }
    public void getCredentials(){
        VIBAreaDomain.updateFoodInfo(food);
    }
    public void attachListeners(Credentials credentials){
        VIBJobInfoVM.attachListeners(credentials);
    }

    public void getJobInfo(VIBJobInterface vibjobinterface) {
        //Log.d(TAG, " here ");
        uc.getJobInfo(vibjobinterface);
        //Log.d(TAG, "here too");
    }
    public void quit(Credentials credentials){
        VIBJobInfoVM.quit(credentials);
    }
}