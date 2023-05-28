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

import com.example.catastrophecompass.DataLayer.Model.WItem;
import com.example.catastrophecompass.DomainLayer.Domain.VIBAreaDomain;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.Disposable;
@HiltViewModel
public class VIBAreaVM extends ViewModel {

    private UC uc;

    private VIBAreaDomain vıbdomain;

    @Inject
    public VIBAreaVM(UC uc) {
        this.uc = uc;
    }
    public void updateFoodInfo (String food){
      VIBAreaDomain.updateFoodInfo(food);
    }
    public void getAreaInfo(VIBAreaInterface VIBinterface) {
        //Log.d(TAG, " here ");
        uc.getAreaInfo(VIBinterface);
        //Log.d(TAG, "here too");
    }
}