package com.example.catastrophecompass.UILayer.TeamLeader;

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
import com.example.catastrophecompass.DomainLayer.Domain.VIBAreaDomain;
import com.example.catastrophecompass.UILayer.Common.VIBAreaInterface;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.Disposable;
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